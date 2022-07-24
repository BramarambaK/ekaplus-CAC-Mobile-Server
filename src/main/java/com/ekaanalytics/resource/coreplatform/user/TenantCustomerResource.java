package com.ekaanalytics.resource.coreplatform.user;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ekaanalytics.resource.BaseResource;
import com.ekaanalytics.resource.UriConstants;

@RestController
@RequestMapping(value="/customers", produces=APPLICATION_JSON_UTF8_VALUE)
public class TenantCustomerResource extends BaseResource{
	
	@RequestMapping(value="/my-profile", method=RequestMethod.GET)
	public ResponseEntity<String> getMyProfile(HttpServletRequest httpRequest) {
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(httpRequest, 
															 UriConstants.GET_MY_PROFILE_URI));
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.GET,
											null,
											headers,
											String.class);
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<String> getActiveFarmers(@RequestParam(required=false) String searchText,
												   HttpServletRequest httpRequest) {
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(httpRequest, 
															 UriConstants.GET_ACTIVE_FARMER_LIST_URI));
		if(!StringUtils.isEmpty(searchText)){
			uriBuilder.queryParam("searchText", searchText);
		}
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.GET,
											null,
											headers,
											String.class);
	}
}
