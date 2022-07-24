package com.ekaanalytics.resource.coreplatform.notification;

import static com.ekaanalytics.constants.CommonConstants.USER_ID_HEADER;
import static com.ekaanalytics.resource.UriConstants.BUSINESS_ALERTS_URI;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ekaanalytics.common.config.ServerConfiguration;
import com.ekaanalytics.resource.BaseResource;
import com.ekaanalytics.wrapper.coreplatform.notification.BusinessAlert;
import com.ekaanalytics.wrapper.coreplatform.notification.NotificationResponse;

@RestController
@RequestMapping("/notifications")
public class NotificationResource extends BaseResource {
	
	private static Map<String, Timestamp> lastAccessTimeMap = new HashMap<>();

	@RequestMapping(value="/business-alerts", method=RequestMethod.GET, produces=APPLICATION_JSON_VALUE)
	public ResponseEntity<NotificationResponse> toggleFavourite(HttpServletRequest httpRequest) {
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(httpRequest, BUSINESS_ALERTS_URI))
												//.queryParam("sort", "[{'property':'TIMESTAMP', 'direction':'DESC'}]")
												.queryParam("maxMobileNotifications", ServerConfiguration.maxMobileNotifications)
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
		
		String userId = httpRequest.getHeader(USER_ID_HEADER);
		//String deviceId = httpRequest.getHeader(DEVICE_ID_HEADER);
		return userId; // + "-" + deviceId;
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
