package com.ekaanalytics.resource.push.notification;

import static com.ekaanalytics.constants.CommonConstants.TENANT_FULL_DOMAIN_HEADER;
import static com.ekaanalytics.resource.UriConstants.PUSH_NOTIFICATION_URI;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ekaanalytics.common.config.ServerConfiguration;
import com.ekaanalytics.resource.BaseResource;

@RestController
@RequestMapping("/device-mappings")
public class PushNotificationResource extends BaseResource{

	@PutMapping(consumes=APPLICATION_JSON_UTF8_VALUE, produces=APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateUserDeviceMapping(HttpServletRequest request, 
														  @RequestBody Map<String, Object> payload) {
		
		HttpHeaders headers = getRequestHeaders(request);
		headers.add(TENANT_FULL_DOMAIN_HEADER, request.getHeader(TENANT_FULL_DOMAIN_HEADER));
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
													.fromHttpUrl(ServerConfiguration.pushNotificationHost)
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
													.fromHttpUrl(ServerConfiguration.pushNotificationHost)
													.path(PUSH_NOTIFICATION_URI);
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(), 
										  HttpMethod.DELETE, 
										  payload, 
										  headers, 
										  Object.class);
	}
}
