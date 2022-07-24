package com.eka.mobileserver.coreplatform.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.eka.mobileserver.constants.UriConstants;

@RestController
@RequestMapping(path = "${api.context}", produces=APPLICATION_JSON_UTF8_VALUE)
public class PasswordController extends BaseController{
	
	/**
	 * 
	 * @param request A json string of below form:
	 {
	    "oldPassword": "Bravo",
	    "newPassword": "Bravo123",
	    "confirmNewPassword": "Bravo123"
	 }
	 * @param httpRequest
	 * @return
	 */
	@RequestMapping(value="/change-password", method=RequestMethod.POST)
	public ResponseEntity<String> changePassword(@RequestBody String request,
												 HttpServletRequest httpRequest) {
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(httpRequest, 
															 UriConstants.CHANGE_URI));
			
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.POST,
											request,
											headers,
											String.class);
	}	
}