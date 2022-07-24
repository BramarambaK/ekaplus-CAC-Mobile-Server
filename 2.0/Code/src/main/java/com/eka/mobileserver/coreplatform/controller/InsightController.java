package com.eka.mobileserver.coreplatform.controller;

import static com.eka.mobileserver.constants.UriConstants.INSIGHTS_URI;
import static com.eka.mobileserver.constants.UriConstants.INSIGHT_DATAVIEWS_NAME_MAP_URI;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.eka.mobileserver.coreplatform.model.InsightResponse;

@RestController
@RequestMapping("${api.context}/insights")
public class InsightController extends BaseController{
	
	
	@RequestMapping(value = "/", method=RequestMethod.GET, produces=APPLICATION_JSON_VALUE)
	public ResponseEntity<InsightResponse[]> getInsights(HttpServletRequest request, 
							@RequestParam Map<String, String> params) {
		
		HttpHeaders headers = getRequestHeaders(request);
		UriComponentsBuilder uriBuilder = getUriBuilder(request, INSIGHTS_URI, params);
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(), 
										  HttpMethod.GET, 
										  null, 
										  headers, 
										  InsightResponse[].class);
	}
	
	@RequestMapping(value = "/{insightId}", method=RequestMethod.GET, produces=APPLICATION_JSON_VALUE)
	public ResponseEntity<InsightResponse> getInsight(HttpServletRequest request, 
			@PathVariable String insightId) {
		
		HttpHeaders headers = getRequestHeaders(request);
		UriComponentsBuilder uriBuilder = getUriBuilder(request, INSIGHTS_URI + "/" + insightId, null);
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(), 
										  HttpMethod.GET, 
										  null, 
										  headers, 
										  InsightResponse.class);
	}
	
	@RequestMapping(value = "/{insightId}/dataview-name-map", method=RequestMethod.GET, produces=APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getDataviewIdNameMap(HttpServletRequest request, 
			@PathVariable String insightId) {
		
		HttpHeaders headers = getRequestHeaders(request);
		UriComponentsBuilder uriBuilder = getUriBuilder(request, 
														String.format(INSIGHT_DATAVIEWS_NAME_MAP_URI, insightId), 
														null);
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(), 
											HttpMethod.GET, 
											null, 
											headers, 
											String.class);
	}
}
