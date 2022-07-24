package com.ekaanalytics.resource.coreplatform.login;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

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
import com.ekaanalytics.resource.UriConstants;

@RestController
@RequestMapping(produces=APPLICATION_JSON_UTF8_VALUE)
public class PasswordResource extends BaseResource{
	
	
	@RequestMapping(value="/user/{userId}", method=RequestMethod.GET)
	public ResponseEntity<String> getUserDetails(@PathVariable String userId,
												     HttpServletRequest httpRequest) {
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(httpRequest, 
															 UriConstants.GET_USER_DATA_URI + userId));
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.GET,
											null,
											headers,
											String.class);
	}
	
	@RequestMapping(value="/user", method=RequestMethod.POST)
	public ResponseEntity<String> saveUser(@RequestBody String request,
										   HttpServletRequest httpRequest) {
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(httpRequest, 
															 UriConstants.SAVE_USER_URI));
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.POST,
											request,
											headers,
											String.class);
	}	
	
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
															 UriConstants.CHANGE_PWD_URI));
			
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.POST,
											request,
											headers,
											String.class);
	}	
	
	@RequestMapping(value="/validatePassword", method=RequestMethod.POST)
	public ResponseEntity<String> validateExistingPassword(@RequestBody String request,
												   		   HttpServletRequest httpRequest) {
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(httpRequest, 
															 UriConstants.VALIDATE_CURR_PWD_URI));
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.POST,
											request,
											headers,
											String.class);
	}	

	/**
	 * Validates the new password against the client's password policy(if any).
	 * 
	 * @param request
	 * @param httpRequest
	 * @return
	 */
	@RequestMapping(value="/validateNewPassword", method=RequestMethod.POST)
	public ResponseEntity<String> validateNewPassword(@RequestBody String request,
												   	  HttpServletRequest httpRequest) {
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(httpRequest, 
															 UriConstants.VALIDATE_NEW_PWD_URI));
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.POST,
											request,
											headers,
											String.class);
	}	
	
	@RequestMapping(value="/getPasswordPolicy", method=RequestMethod.GET)
	public ResponseEntity<String> getPasswordPolicy(HttpServletRequest httpRequest) {
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(httpRequest, 
															 UriConstants.GET_PWD_POLICY ));
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.GET,
											null,
											headers,
											String.class);
	}
	
	@RequestMapping(value="/passwordValidator", method=RequestMethod.POST)
	public ResponseEntity<String> passwordValidator(@RequestBody String request,
												   	  HttpServletRequest httpRequest) {
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(httpRequest, 
															 UriConstants.VALIDATE_AGAINST_PWD_POLICY));
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.POST,
											request,
											headers,
											String.class);
	}
}
