package com.eka.mobileserver.common;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Qualifier("baseHttpClient")
@Component
public class BaseHttpClient {
	
	protected RestTemplate restTemplate;
	
	@PostConstruct
	private void initialize() {

		restTemplate = new RestTemplate();
		HttpMessageConverter<Resource> resource = new ResourceHttpMessageConverter();
		FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
		formHttpMessageConverter.addPartConverter(new MappingJackson2HttpMessageConverter(new CustomObjectMapper()));
		formHttpMessageConverter.addPartConverter(resource);
		
		List<HttpMessageConverter<?>> modifiedConverters = new ArrayList<>();
		modifiedConverters.addAll(restTemplate.getMessageConverters());
		modifiedConverters.add(0, formHttpMessageConverter);
		modifiedConverters.add(1, resource);
		
		restTemplate.setMessageConverters(modifiedConverters);
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
	}
	
	public <T, E> ResponseEntity<E> fireHttpRequest(URI uri, HttpMethod method, T requestBody, 
			HttpHeaders headers, Class<E> responseEntityClass) {
		
		HttpEntity<T> request = new HttpEntity<>(requestBody, headers);
		return restTemplate.exchange(uri, method, request, responseEntityClass);
	}	
}