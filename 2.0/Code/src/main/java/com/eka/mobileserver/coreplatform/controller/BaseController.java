package com.eka.mobileserver.coreplatform.controller;

import static com.eka.mobileserver.constants.CommonConstants.ACCEPT_ENCODING_HEADER;
import static com.eka.mobileserver.constants.CommonConstants.AUTHORIZATION_HEADER;
import static com.eka.mobileserver.constants.CommonConstants.CONTENT_TYPE_HEADER;
import static com.eka.mobileserver.constants.CommonConstants.DEVICE_ID_HEADER;
import static com.eka.mobileserver.constants.CommonConstants.REMOTE_USER_HEADER;
import static com.eka.mobileserver.constants.CommonConstants.USER_AGENT_HEADER;
import static com.eka.mobileserver.constants.MessageConstants.INAPPROPRIATE_API_INVOCATION;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.util.UriComponentsBuilder;

import com.eka.mobileserver.common.BaseHttpClient;
import com.eka.mobileserver.common.CACErrorResponse;
import com.eka.mobileserver.constants.CommonConstants;
import com.eka.mobileserver.exception.FunctionalException;


@RestController
public class BaseController {
	
	final static  Logger logger = ESAPI.getLogger(BaseController.class);
	
	@Autowired
	@Qualifier("baseHttpClient")
	protected BaseHttpClient httpClient;
	
	@Value("${rateLimitingHeaderValue}")
	private String rateLimitingHeaderValue;

	protected <T> ResponseEntity<T> getResponse(T body) {
		
		return new ResponseEntity<>(body, getDefaultHeaders(), HttpStatus.OK);
	}
	
	public HttpHeaders getDefaultHeaders() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(CONTENT_TYPE_HEADER, MediaType.APPLICATION_JSON_VALUE);
		return headers;
	}
	
	public HttpHeaders getRequestHeaders(HttpServletRequest request) {
		
		HttpHeaders headers = getDefaultHeaders();
		headers.set(ACCEPT_ENCODING_HEADER, request.getHeader(ACCEPT_ENCODING_HEADER));
		headers.set(AUTHORIZATION_HEADER, request.getHeader(AUTHORIZATION_HEADER));
		headers.set(USER_AGENT_HEADER, request.getHeader(USER_AGENT_HEADER));
		headers.set(DEVICE_ID_HEADER, request.getHeader(DEVICE_ID_HEADER));
		headers.set(REMOTE_USER_HEADER, rateLimitingHeaderValue);
		return headers;
	}

	
	protected String getURIForCACWebRequest(HttpServletRequest request, String apiURI) {
	   
		String tenantDomain = request.getHeader(CommonConstants.TENANT_FULL_DOMAIN_HEADER);
		
		// Ad hoc change to allow android app users to log in
		if(tenantDomain == null){
			tenantDomain = CommonConstants.DEFAULT_CCRI_DOMAIN;
		}
		return tenantDomain + apiURI;
	}
	
	protected UriComponentsBuilder getUriBuilder(HttpServletRequest request, String uri,
			Map<String, String> params) {
		
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(request, uri));

		if(params != null){
			for (Map.Entry<String, String> entry : params.entrySet()) {
				uriBuilder.queryParam(entry.getKey(), entry.getValue());
			}
		}
		return uriBuilder;
	}
	
	protected void validateParams(Map<String, String> params, String... paramKeys) throws FunctionalException {
		
		for (String paramKey : paramKeys) {
			if(!params.containsKey(paramKey))
				throw new FunctionalException(INAPPROPRIATE_API_INVOCATION, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	protected <T> ResponseEntity<T> getResponse(T body, HttpStatus httpStatus) {
		
		return new ResponseEntity<>(body, getDefaultHeaders(), httpStatus);
	}
	
	@ExceptionHandler(FunctionalException.class)
	protected ResponseEntity<CACErrorResponse> handleFunctionalException(FunctionalException fe) {
		
		logger.error(Logger.EVENT_FAILURE,"Functional Exception: " + fe.getMessage(), fe);
		return getResponse(new CACErrorResponse(fe.getMessage()), fe.getErrorStatus());
	}
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<CACErrorResponse> handleException(Exception e) {
		
		HttpStatus statusCode;
		String msg;
		logger.error(Logger.EVENT_FAILURE,"Error: " + e.getMessage(), e);
		if(e instanceof HttpStatusCodeException){
			HttpStatusCodeException httpException = (HttpStatusCodeException)e;
			msg = httpException.getResponseBodyAsString();
			statusCode = httpException.getStatusCode();
			try {
				JSONObject msgJson = new JSONObject(msg);
				if(msgJson.has("msg") && !StringUtils.isEmpty(msgJson.getString("msg"))) {
					msg = msgJson.getString("msg");
				}else if(msgJson.has("error_description") && !StringUtils.isEmpty(msgJson.getString("error_description"))) {
					msg = msgJson.getString("error_description");
				}
			}catch(JSONException ex) {
				// Do nothing
			}
		} else if (e instanceof ResourceAccessException){
			msg = "Could not contact the server. Please try after some time.";
			statusCode = HttpStatus.SERVICE_UNAVAILABLE;
		}else {
			msg = "Something went wrong! Please contact support if the issue persists.";
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return getResponse(new CACErrorResponse(msg), statusCode);
	}
}
