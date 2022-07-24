package com.eka.mobileserver.pushnotification.controller;

import static com.eka.mobileserver.constants.CommonConstants.TENANT_FULL_DOMAIN_HEADER;
import static com.eka.mobileserver.constants.UriConstants.PUSH_NOTIFICATION_URI;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.eka.mobileserver.coreplatform.controller.BaseController;

@RestController
@RequestMapping("${api.context}/device-mappings")
public class PushNotificationController extends BaseController{

	@Value("${pushNotificationHost}")
	private String pushNotificationHost;
	
	@PutMapping(consumes=APPLICATION_JSON_UTF8_VALUE, produces=APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateUserDeviceMapping(HttpServletRequest request, 
														  @RequestBody Map<String, Object> payload) {
		
		HttpHeaders headers = getRequestHeaders(request);
		headers.add(TENANT_FULL_DOMAIN_HEADER, request.getHeader(TENANT_FULL_DOMAIN_HEADER));
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
													.fromHttpUrl(pushNotificationHost)
													.path(PUSH_NOTIFICATION_URI);
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(), 
										  HttpMethod.PUT, 
										  payload, 
										  headers, 
										  Object.class);
	}
	
	@DeleteMapping(consumes=APPLICATION_JSON_UTF8_VALUE, produces=APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> deleteDeviceMapping(HttpServletRequest request, 
													  @RequestBody Map<String, Object> payload) {
		
		HttpHeaders headers = getRequestHeaders(request);
		headers.add(TENANT_FULL_DOMAIN_HEADER, request.getHeader(TENANT_FULL_DOMAIN_HEADER));
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
													.fromHttpUrl(pushNotificationHost)
													.path(PUSH_NOTIFICATION_URI);
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(), 
										  HttpMethod.DELETE, 
										  payload, 
										  headers, 
										  Object.class);
	}
}
