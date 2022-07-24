package com.ekaanalytics.resource.coreplatform.login;

import static com.ekaanalytics.constants.CommonConstants.ACCESS_TOKEN;
import static com.ekaanalytics.constants.CommonConstants.AUTHORIZATION_HEADER;
import static com.ekaanalytics.constants.CommonConstants.CLIENT_ID;
import static com.ekaanalytics.constants.CommonConstants.CLIENT_NAME;
import static com.ekaanalytics.constants.CommonConstants.DEVICE_ID_HEADER;
import static com.ekaanalytics.constants.CommonConstants.EMAIL;
import static com.ekaanalytics.constants.CommonConstants.FIRST_NAME;
import static com.ekaanalytics.constants.CommonConstants.ID;
import static com.ekaanalytics.constants.CommonConstants.LAST_NAME;
import static com.ekaanalytics.constants.CommonConstants.MSG;
import static com.ekaanalytics.constants.CommonConstants.OTP_TOKEN;
import static com.ekaanalytics.constants.CommonConstants.TENANT_INFO;
import static com.ekaanalytics.constants.CommonConstants.TENANT_SHORT_NAME;
import static com.ekaanalytics.constants.CommonConstants.USERNAME;
import static com.ekaanalytics.constants.CommonConstants.USER_TYPE;
import static com.ekaanalytics.constants.CommonConstants.VERIFY_MFA;
import static com.ekaanalytics.resource.UriConstants.CAC_SECURITY_API;
import static com.ekaanalytics.resource.UriConstants.CAC_SECURITY_TOKEN_URI;
import static com.ekaanalytics.resource.UriConstants.CAC_SECURITY_USER_INFO_URI;
import static com.ekaanalytics.resource.UriConstants.CAC_MFA_REGENRATEOTP_API;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ekaanalytics.exception.FunctionalException;
import com.ekaanalytics.resource.BaseResource;
import com.ekaanalytics.wrapper.login.AccessTokenResponse;
import com.ekaanalytics.wrapper.login.MobileLoginResponse;
import com.ekaanalytics.wrapper.login.UserInfo;

@RestController
public class MFAResource extends BaseResource{

	private static final Log logger = LogFactory.getLog(MFAResource.class);

	
	@RequestMapping(value = "/settings", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getSettings(HttpServletRequest request, 
			@RequestParam Map<String, String> params) throws FunctionalException  {

		logger.info("Setting's API Invoked");
		
		JSONObject response = new JSONObject();
		Object settingsResponse = new Object();
		try {
			//HttpHeaders headers = new HttpHeaders();
			//headers.set(TENANT_FULL_DOMAIN_HEADER, request.getHeader(TENANT_FULL_DOMAIN_HEADER));
			UriComponentsBuilder tokenUriBuilder = UriComponentsBuilder
					.fromHttpUrl(getURIForCACWebRequest(request, CAC_SECURITY_API));
				
			MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
			requestParams.setAll(params);

			tokenUriBuilder.queryParams(requestParams);

			settingsResponse = httpClient.fireHttpRequest(tokenUriBuilder.build().encode().toUri(), 
					HttpMethod.GET, 
					null, 
					null, 
					Object.class).getBody();
			
			logger.info("Setting's API Invocation Complete");
			
			return getResponse(settingsResponse);

		} catch (JSONException e) {
			logger.error(e.getMessage(), e);
			String errorMsg = response.has(MSG) ? response.optString(MSG) : e.getMessage();
			throw new FunctionalException(errorMsg, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/unique-token", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getToken(HttpServletRequest request, 
			@RequestParam Map<String, String> params) throws FunctionalException  {
		
		logger.info("Unique token's API Invoked");

		JSONObject response = new JSONObject();
		Object tokenresponse = new Object();
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set(AUTHORIZATION_HEADER, request.getHeader(AUTHORIZATION_HEADER));
			headers.set(DEVICE_ID_HEADER, request.getHeader(DEVICE_ID_HEADER));
			headers.set(VERIFY_MFA, request.getHeader(VERIFY_MFA));
			headers.set(OTP_TOKEN, request.getHeader(OTP_TOKEN));
			
			UriComponentsBuilder tokenUriBuilder = UriComponentsBuilder
					.fromHttpUrl(getURIForCACWebRequest(request, CAC_SECURITY_TOKEN_URI));
				
			MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
			requestParams.setAll(params);
			
			tokenresponse = httpClient.fireHttpRequest(tokenUriBuilder.build().encode().toUri(), 
					HttpMethod.POST, 
					requestParams, 
					headers, 
					Object.class).getBody();
			
			logger.info("Unique token's API Invocation Complete");

			return getResponse(tokenresponse);

		} catch (JSONException e) {
			logger.error(e.getMessage(), e);
			String errorMsg = response.has(MSG) ? response.optString(MSG) : e.getMessage();
			throw new FunctionalException(errorMsg, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@RequestMapping(value = "/userinfo", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getUserInfo(HttpServletRequest request, 
			@RequestParam Map<String, String> params) throws FunctionalException  {
		JSONObject response = new JSONObject();

		try {
			
			logger.info("Token's API Invoked");

			HttpHeaders headers = new HttpHeaders();
			headers.set(AUTHORIZATION_HEADER, request.getHeader(AUTHORIZATION_HEADER));
			headers.set(DEVICE_ID_HEADER, request.getHeader(DEVICE_ID_HEADER));
			headers.set(VERIFY_MFA, request.getHeader(VERIFY_MFA));
			headers.set(OTP_TOKEN, request.getHeader(OTP_TOKEN));
			
			UriComponentsBuilder tokenUriBuilder = UriComponentsBuilder
					.fromHttpUrl(getURIForCACWebRequest(request, CAC_SECURITY_TOKEN_URI));
				
			MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
			requestParams.setAll(params);
			
			AccessTokenResponse tokenResponse = httpClient.fireHttpRequest(tokenUriBuilder.build().encode().toUri(), 
					HttpMethod.POST, 
					requestParams, 
					headers, 
					AccessTokenResponse.class).getBody();

			logger.info("Token's API Invocation Complete");
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

	
	@RequestMapping(value = "/regenerate-otp", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> regenerateOTP(HttpServletRequest request, 
			@RequestParam Map<String, String> params, @RequestBody Object requestBody) throws FunctionalException  {
		JSONObject response = new JSONObject();
		Object otpResponse = new Object();
		try {
			
			logger.info("regenerate-otp API Invoked");
			
			HttpHeaders headers = new HttpHeaders();
			headers.set(DEVICE_ID_HEADER, request.getHeader(DEVICE_ID_HEADER));
			
			UriComponentsBuilder tokenUriBuilder = UriComponentsBuilder
					.fromHttpUrl(getURIForCACWebRequest(request, CAC_MFA_REGENRATEOTP_API));
				
			MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
			requestParams.setAll(params);
			
			tokenUriBuilder.queryParams(requestParams);

			otpResponse = httpClient.fireHttpRequest(tokenUriBuilder.build().encode().toUri(), 
					HttpMethod.POST, 
					requestBody, 
					headers, 
					Object.class).getBody();

			logger.info("regenerate-otp API Invocation Complete");
			
			return getResponse(otpResponse);

		} catch (JSONException e) {
			logger.error(e.getMessage(), e);
			String errorMsg = response.has(MSG) ? response.optString(MSG) : e.getMessage();
			throw new FunctionalException(errorMsg, HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
