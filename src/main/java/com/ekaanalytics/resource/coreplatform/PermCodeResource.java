package com.ekaanalytics.resource.coreplatform;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ekaanalytics.resource.BaseResource;
import com.ekaanalytics.resource.UriConstants;

@RestController
@RequestMapping(value="/permcodes", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PermCodeResource extends BaseResource{
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<String> getCurrentUserPermCodes(HttpServletRequest httpRequest) {
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(httpRequest, 
															 UriConstants.PERM_CODES_API));
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.GET,
											null,
											headers,
											String.class);
	}
	
	@RequestMapping(value="/{appId}", method=RequestMethod.GET)
	public ResponseEntity<String> getUserPermCodesForApp(@PathVariable Integer appId,
														 HttpServletRequest httpRequest) {
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(httpRequest, 
															 UriConstants.PERM_CODES_API) + appId);
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.GET,
											null,
											headers,
											String.class);
	}

}
