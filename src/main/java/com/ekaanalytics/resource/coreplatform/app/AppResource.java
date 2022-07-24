package com.ekaanalytics.resource.coreplatform.app;

import static com.ekaanalytics.constants.CommonConstants.APP_TYPE;
import static com.ekaanalytics.constants.CommonConstants.APP_TYPE_BOTH;
import static com.ekaanalytics.constants.CommonConstants.CATEGORY_ID;
import static com.ekaanalytics.constants.CommonConstants.ONLY_FAVOURITE;
import static com.ekaanalytics.resource.UriConstants.APPS_URI;
import static com.ekaanalytics.resource.UriConstants.APP_CATEGORIES_INFO_URI;

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
import com.ekaanalytics.wrapper.coreplatform.app.AppResponse;

@RestController
@RequestMapping("/apps")
public class AppResource extends BaseResource{
	
	@RequestMapping(value = "/favourite", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppResponse[]> getFavouriteApps(HttpServletRequest request) {
		
		HttpHeaders headers = getRequestHeaders(request);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(request, APPS_URI))
												.queryParam(ONLY_FAVOURITE, true)
												.queryParam(APP_TYPE, APP_TYPE_BOTH);
										
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(), 
											HttpMethod.GET, 
											null, 
											headers,
											AppResponse[].class);
	}
	
	@RequestMapping(value = "/{categoryId}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppResponse[]> getAppsByCategory(HttpServletRequest request, 
													 	   @PathVariable String categoryId) {
		
		HttpHeaders headers = getRequestHeaders(request);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(request, APPS_URI))
												.queryParam(CATEGORY_ID, categoryId)
												.queryParam(APP_TYPE, APP_TYPE_BOTH);
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(), 
										  HttpMethod.GET, 
										  null, 
										  headers,
										  AppResponse[].class);
	}
	
	
	
	@RequestMapping(value= "/categoryinfo", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getAppCategoryInfo(HttpServletRequest request){
		
		HttpHeaders headers = getRequestHeaders(request);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(request, APP_CATEGORIES_INFO_URI));
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
										  HttpMethod.GET,
										  null,
										  headers,
										  String.class);
	}

}
