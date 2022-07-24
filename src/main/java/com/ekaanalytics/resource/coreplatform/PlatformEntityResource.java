package com.ekaanalytics.resource.coreplatform;

import static com.ekaanalytics.resource.UriConstants.TOGGLE_FAVOURITE_URI;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ekaanalytics.resource.BaseResource;

@RestController
@RequestMapping("/entities")
public class PlatformEntityResource extends BaseResource {

	@RequestMapping(value="/{moduleName}/{entityId}/toggle-favourite", method=RequestMethod.POST, produces=APPLICATION_JSON_VALUE)
	public ResponseEntity<String> toggleFavourite(@PathVariable(value="moduleName") String moduleName,
												  @PathVariable(value="entityId")   String entityId,
												  @RequestBody String request,
												  HttpServletRequest httpRequest) {
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(httpRequest, 
															 String.format(TOGGLE_FAVOURITE_URI, moduleName, entityId)));
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.POST,
											request,
											headers,
											String.class);
	}
	
}
