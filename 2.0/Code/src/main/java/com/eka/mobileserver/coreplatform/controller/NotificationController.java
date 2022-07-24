package com.eka.mobileserver.coreplatform.controller;

import static com.eka.mobileserver.constants.UriConstants.BUSINESS_ALERTS_URI;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.eka.mobileserver.coreplatform.model.BusinessAlert;
import com.eka.mobileserver.coreplatform.model.NotificationResponse;

@RestController
@RequestMapping("${api.context}/notifications")
public class NotificationController extends BaseController {
	
	private static Map<String, Timestamp> lastAccessTimeMap = new HashMap<>();
	
	@Value("${maxMobileNotifications}")
	private String maxMobileNotifications;

	@RequestMapping(value="/business-alerts", method=RequestMethod.GET, produces=APPLICATION_JSON_VALUE)
	public ResponseEntity<NotificationResponse> toggleFavourite(HttpServletRequest httpRequest) {
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(httpRequest, BUSINESS_ALERTS_URI))
												//.queryParam("sort", "[{'property':'TIMESTAMP', 'direction':'DESC'}]")
												.queryParam("maxMobileNotifications", maxMobileNotifications)
												.queryParam("isMobileClient", true);
		
		
		ResponseEntity<NotificationResponse> response = 
									httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
																HttpMethod.GET,
																null,
																headers,
																NotificationResponse.class);
		updateUnseenLabel(response.getBody(), getCacheKeyForUser(httpRequest));
		return response;
	}

	private String getCacheKeyForUser(HttpServletRequest httpRequest) {
		
		String accessToken = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
		JSONObject payload = new JSONObject(new String(Base64.getDecoder().decode(accessToken.split("\\.")[1].getBytes()), StandardCharsets.UTF_8));
		return payload.getString("user_name");
	}

	private void updateUnseenLabel(NotificationResponse response, String cacheKey) {
		
		short unseenCount = 0;
		Timestamp lastAccessTime = lastAccessTimeMap.get(cacheKey);
		lastAccessTimeMap.put(cacheKey, new Timestamp(System.currentTimeMillis()));
		
		if(lastAccessTime == null){
			response.setUnseenCount(response.getTotalCount());
			return;
		}
		
		List<BusinessAlert> businessAlerts = response.getBusinessAlerts();
		for (BusinessAlert businessAlert : businessAlerts) {
			if(businessAlert.getTimestamp().after(lastAccessTime))
				++unseenCount;
		}
		response.setUnseenCount(unseenCount);
	}
}