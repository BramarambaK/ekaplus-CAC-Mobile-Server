package com.ekaanalytics.resource.farmerconnect;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ekaanalytics.exception.FunctionalException;
import com.ekaanalytics.resource.BaseResource;
import com.ekaanalytics.webclient.BaseHttpClient;

@RestController
public class GenericWrapperController extends BaseResource{
	
	private static final String GENRIC_WRAPPER_CONTEXT = "/farmerconnect/generic";
	private static final String PLATFORM_API_CONTEXT = "/spring";
	
	@Autowired
	private BaseHttpClient httpClient;
	
	@GetMapping(value = GENRIC_WRAPPER_CONTEXT + "/**" , consumes=APPLICATION_JSON_UTF8_VALUE, produces=APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> handleGet(@RequestParam(required = false) MultiValueMap<String, String> queryParams,
											HttpServletRequest request) throws FunctionalException {
		
		String exposedURI = request.getRequestURI().split(GENRIC_WRAPPER_CONTEXT)[1];
		HttpHeaders headers = getRequestHeaders(request);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(request, 
															 PLATFORM_API_CONTEXT + exposedURI))
												.queryParams(queryParams);
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.GET,
											null,
											headers,
											Object.class);
	}

	@PutMapping(value = GENRIC_WRAPPER_CONTEXT + "/**" , consumes=APPLICATION_JSON_UTF8_VALUE, produces=APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> handlePost(@RequestBody(required = false) String requestBody, 
										 	 @RequestParam(required = false) MultiValueMap<String, String> queryParams,
										 	 HttpServletRequest request) throws FunctionalException {

		String exposedURI = request.getRequestURI().split(GENRIC_WRAPPER_CONTEXT)[1];
		HttpHeaders headers = getRequestHeaders(request);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(request, 
															 PLATFORM_API_CONTEXT + exposedURI))
												.queryParams(queryParams);
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.PUT,
											requestBody,
											headers,
											Object.class);
	}
	
	//@PostMapping(value = GENRIC_WRAPPER_CONTEXT + "/collections" , consumes=APPLICATION_JSON_UTF8_VALUE, produces=APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> handlePut(@RequestBody(required = false) String requestBody){
	
		return new ResponseEntity<Object>(new JSONObject().put("success", "yeh hai jalwa!").toString(), HttpStatus.OK);
	}
	
	@PostMapping(value = GENRIC_WRAPPER_CONTEXT + "/**" , consumes=APPLICATION_JSON_UTF8_VALUE, produces=APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> handlePut(@RequestBody(required = false) String requestBody, 
											@RequestParam(required = false) MultiValueMap<String, String> queryParams,
											HttpServletRequest request) throws FunctionalException {
		
		String exposedURI = request.getRequestURI().split(GENRIC_WRAPPER_CONTEXT)[1];
		HttpHeaders headers = getRequestHeaders(request);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(request, 
														PLATFORM_API_CONTEXT + exposedURI))
												.queryParams(queryParams);
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
				HttpMethod.POST,
				requestBody,
				headers,
				Object.class);
	}
	
}
