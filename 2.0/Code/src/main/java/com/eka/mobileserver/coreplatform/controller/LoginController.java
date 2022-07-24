package com.eka.mobileserver.coreplatform.controller;

import static com.eka.mobileserver.constants.CommonConstants.ACCESS_TOKEN;
import static com.eka.mobileserver.constants.CommonConstants.AUTHORIZATION_HEADER;
import static com.eka.mobileserver.constants.CommonConstants.CLIENT_ID;
import static com.eka.mobileserver.constants.CommonConstants.CLIENT_NAME;
import static com.eka.mobileserver.constants.CommonConstants.DEVICE_ID_HEADER;
import static com.eka.mobileserver.constants.CommonConstants.EMAIL;
import static com.eka.mobileserver.constants.CommonConstants.FIRST_NAME;
import static com.eka.mobileserver.constants.CommonConstants.GRANT_TYPE;
import static com.eka.mobileserver.constants.CommonConstants.ID;
import static com.eka.mobileserver.constants.CommonConstants.LAST_NAME;
import static com.eka.mobileserver.constants.CommonConstants.MSG;
import static com.eka.mobileserver.constants.CommonConstants.OAUTH_CLIENT_ID;
import static com.eka.mobileserver.constants.CommonConstants.TENANT_INFO;
import static com.eka.mobileserver.constants.CommonConstants.TENANT_SHORT_NAME;
import static com.eka.mobileserver.constants.CommonConstants.USERNAME;
import static com.eka.mobileserver.constants.CommonConstants.USER_TYPE;
import static com.eka.mobileserver.constants.UriConstants.CAC_SECURITY_LOGOUT_URI;
import static com.eka.mobileserver.constants.UriConstants.CAC_SECURITY_TOKEN_URI;
import static com.eka.mobileserver.constants.UriConstants.CAC_SECURITY_USER_INFO_URI;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.eka.mobileserver.coreplatform.model.AccessTokenResponse;
import com.eka.mobileserver.coreplatform.model.MobileLoginResponse;
import com.eka.mobileserver.coreplatform.model.UserInfo;
import com.eka.mobileserver.exception.FunctionalException;

@RestController
@RequestMapping("${api.context}")
public class LoginController extends BaseController{
	
	private static final Log logger = LogFactory.getLog(LoginController.class);
	
	//private WebResponseExceptionTranslator providerExceptionHandler = new DefaultWebResponseExceptionTranslator();
	
	/**
	 * API Endpoint for mobile login.
	 * 
	 * @param params		- OAUTH_CLIENT_ID, GRANT_TYPE, DEVICE_ID
	 * @return
	 * @throws FunctionalException
	 */
	@RequestMapping(value = "/login", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> login(HttpServletRequest request, 
													 @RequestParam Map<String, String> params) throws FunctionalException  {
		
		validateParams(params, OAUTH_CLIENT_ID, GRANT_TYPE);
		JSONObject response = new JSONObject();
		try {
		// Get access token
		HttpHeaders headers = new HttpHeaders();
		headers.set(AUTHORIZATION_HEADER, request.getHeader(AUTHORIZATION_HEADER));
		headers.set(DEVICE_ID_HEADER, request.getHeader(DEVICE_ID_HEADER));
		
		UriComponentsBuilder tokenUriBuilder = UriComponentsBuilder
													.fromHttpUrl(getURIForCACWebRequest(request, CAC_SECURITY_TOKEN_URI));
		
		MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.setAll(params);
		AccessTokenResponse tokenResponse = httpClient.fireHttpRequest(tokenUriBuilder.build().encode().toUri(), 
																	   HttpMethod.POST, 
																	   requestParams, 
																	   headers, 
																	   AccessTokenResponse.class).getBody();
		
		// Get user info
		headers = getDefaultHeaders();
		headers.set(AUTHORIZATION_HEADER, String.valueOf(tokenResponse.getTokenInfo().get(ACCESS_TOKEN)));
		headers.set(DEVICE_ID_HEADER, request.getHeader(DEVICE_ID_HEADER));
		
		UriComponentsBuilder userInfoUriBuilder = UriComponentsBuilder
													.fromHttpUrl(getURIForCACWebRequest(request, CAC_SECURITY_USER_INFO_URI));
		
		response = new JSONObject(httpClient.fireHttpRequest(userInfoUriBuilder.build().encode().toUri(), 
											 HttpMethod.GET,
											 null,
											 headers, 
											 String.class).getBody());
		
		JSONObject tenantInfo = response.getJSONObject(TENANT_INFO);
		return getResponse(new MobileLoginResponse(tokenResponse, 
												  new UserInfo(response.getInt(ID),
														  	   response.getString(USERNAME), 
														  	   response.getString(EMAIL),
														  	   response.getString(FIRST_NAME),
														  	   response.getString(LAST_NAME),
														  	   response.getInt(USER_TYPE),
														  	   tenantInfo.getInt(CLIENT_ID),
														  	   tenantInfo.getString(TENANT_SHORT_NAME),
												  			   tenantInfo.getString(CLIENT_NAME))));
		} catch (JSONException e) {
			logger.error(e.getMessage(), e);
			String errorMsg = response.has(MSG) ? response.optString(MSG) : e.getMessage();
			throw new FunctionalException(errorMsg, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public ResponseEntity<String> logout(HttpServletRequest httpRequest) {
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		headers.set("refresh_token", httpRequest.getHeader("refresh_token"));

		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(httpRequest, 
															 CAC_SECURITY_LOGOUT_URI));
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.POST,
											null,
											headers,
											String.class);
	}
}
