package com.eka.mobileserver.coreplatform.controller;

import static com.eka.mobileserver.constants.CommonConstants.APP;
import static com.eka.mobileserver.constants.CommonConstants.APP_TYPE;
import static com.eka.mobileserver.constants.CommonConstants.APP_TYPE_BOTH;
import static com.eka.mobileserver.constants.CommonConstants.FILTER;
import static com.eka.mobileserver.constants.CommonConstants.INSIGHT;
import static com.eka.mobileserver.constants.CommonConstants.SEARCH_BY;
import static com.eka.mobileserver.constants.UriConstants.APPS_URI;
import static com.eka.mobileserver.constants.UriConstants.INSIGHTS_URI;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.eka.mobileserver.coreplatform.model.SearchListResponse;

@RestController
@RequestMapping("${api.context}/search")
public class SearchController extends BaseController{
	
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SearchListResponse[]> getAppsByCategory(HttpServletRequest request, 
													 	   		 @RequestParam String searchBy) {
		
		HttpHeaders headers = getRequestHeaders(request);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(request, APPS_URI))
												.queryParam(SEARCH_BY, searchBy.toLowerCase())
												.queryParam(APP_TYPE, APP_TYPE_BOTH);
		
		SearchListResponse[] apps = httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(), 
														  HttpMethod.GET, 
														  null, 
														  headers,
														  SearchListResponse[].class).getBody();
		appendEntityType(apps, APP);
		
		UriComponentsBuilder insightUriBuilder = UriComponentsBuilder
													.fromHttpUrl(getURIForCACWebRequest(request, INSIGHTS_URI))
													.queryParam(FILTER, searchBy.toLowerCase());
		
		SearchListResponse[] insights = httpClient.fireHttpRequest(insightUriBuilder.build().encode().toUri(), 
															  HttpMethod.GET, 
															  null, 
															  headers, 
															  SearchListResponse[].class).getBody();
		appendEntityType(insights, INSIGHT);
		
		return new ResponseEntity<>(ArrayUtils.addAll(apps, insights), HttpStatus.OK);
	}

	private void appendEntityType(SearchListResponse[] listResponses, String entityType) {
		
		for (int i = 0; i < listResponses.length; i++) {
			listResponses[i].setEntityType(entityType);
		}
	}	
}
