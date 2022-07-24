package com.eka.mobileserver.coreplatform.controller;

import static com.eka.mobileserver.constants.UriConstants.DATAVIEWS_BASIC_FILTER;
import static com.eka.mobileserver.constants.UriConstants.DATAVIEWS_URI;
import static com.eka.mobileserver.constants.UriConstants.DATAVIEWS_VISUALIZE;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("${api.context}/dataviews")
public class DataviewController extends BaseController{
	
	
	@RequestMapping(value = "/", method=RequestMethod.GET, produces=APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> getDataviews(HttpServletRequest request, 
							@RequestParam Map<String, String> params) {
		
		HttpHeaders headers = getRequestHeaders(request);
		UriComponentsBuilder uriBuilder = getUriBuilder(request, DATAVIEWS_URI, params);
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(), 
										  HttpMethod.GET, 
										  null, 
										  headers, 
										  String.class);
	}
	
	@RequestMapping(value = "/{dataviewId}", method=RequestMethod.GET, produces=APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> getDataview(HttpServletRequest request, 
			@PathVariable int dataviewId) {
		
		HttpHeaders headers = getRequestHeaders(request);
		UriComponentsBuilder uriBuilder = getUriBuilder(request, DATAVIEWS_URI+"/"+dataviewId, null);
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(), 
										  HttpMethod.GET, 
										  null, 
										  headers, 
										  String.class);
	}
	
	@RequestMapping(value = "/visualize", method=RequestMethod.POST, produces=APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> visualize(HttpServletRequest request, 
			@RequestBody String jsonRequest) {
		
		HttpHeaders headers = getRequestHeaders(request);
		UriComponentsBuilder uriBuilder = getUriBuilder(request, DATAVIEWS_VISUALIZE, null);
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(), 
										  HttpMethod.POST, 
										  jsonRequest, 
										  headers, 
										  String.class);
	}

	@RequestMapping(value = "/column-values", method=RequestMethod.POST, produces=APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> getBasicFilter(HttpServletRequest request, 
			@RequestBody String jsonRequest) {
		
		HttpHeaders headers = getRequestHeaders(request);
		UriComponentsBuilder uriBuilder = getUriBuilder(request, DATAVIEWS_BASIC_FILTER, null);
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(), 
										  HttpMethod.POST, 
										  jsonRequest, 
										  headers, 
										  String.class);
	}

}
