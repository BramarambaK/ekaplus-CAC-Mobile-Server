package com.eka.mobileserver.farmerconnect.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.eka.mobileserver.constants.UriConstants;
import com.eka.mobileserver.coreplatform.controller.BaseController;

/**
 * The Class SettingController.
 */
@RestController
@RequestMapping(path = "${api.context}/farmerconnect", produces=APPLICATION_JSON_UTF8_VALUE)
public class SettingController extends BaseController{
	
	
	@GetMapping(value="/general-settings", produces = APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getAppGeneralSettings(HttpServletRequest httpRequest) {
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(httpRequest, 
															 UriConstants.FC_SETTINGS_URI));
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.GET,
											null,
											headers,
											Object.class);
	}
}