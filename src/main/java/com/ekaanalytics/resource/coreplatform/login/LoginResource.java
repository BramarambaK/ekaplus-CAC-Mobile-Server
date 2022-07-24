package com.ekaanalytics.resource.coreplatform.login;

import static com.ekaanalytics.constants.CommonConstants.AUTHORIZATION_HEADER;
import static com.ekaanalytics.constants.CommonConstants.REFRESH_TOKEN_HEADER;
import static com.ekaanalytics.constants.CommonConstants.CLIENT_ID;
import static com.ekaanalytics.constants.CommonConstants.CLIENT_NAME;
import static com.ekaanalytics.constants.CommonConstants.DEVICE_ID_HEADER;
import static com.ekaanalytics.constants.CommonConstants.EMAIL;
import static com.ekaanalytics.constants.CommonConstants.FIRST_NAME;
import static com.ekaanalytics.constants.CommonConstants.GRANT_TYPE;
import static com.ekaanalytics.constants.CommonConstants.MSG;
import static com.ekaanalytics.constants.CommonConstants.OAUTH_CLIENT_ID;
import static com.ekaanalytics.constants.CommonConstants.STATIC_TENANT_NAME;
import static com.ekaanalytics.constants.CommonConstants.TENANT_SHORT_NAME;
import static com.ekaanalytics.constants.CommonConstants.USERNAME;
import static com.ekaanalytics.constants.CommonConstants.USER_ID;
import static com.ekaanalytics.constants.CommonConstants.USER_TYPE;
import static com.ekaanalytics.constants.MessageConstants.WRONG_CREDENTIALS;
import static com.ekaanalytics.resource.UriConstants.CAC_SECURITY_LOGOUT_URI;
import static com.ekaanalytics.resource.UriConstants.CAC_SECURITY_TOKEN_URI;
import static com.ekaanalytics.resource.UriConstants.CAC_WEB_SERVER_LOGIN_URI;
import static com.ekaanalytics.resource.UriConstants.CAC_SECURITY_USER_INFO_URI;
import static com.ekaanalytics.constants.CommonConstants.ID;
import static com.ekaanalytics.constants.CommonConstants.LAST_NAME;
import static com.ekaanalytics.constants.CommonConstants.TENANT_INFO;
import static com.ekaanalytics.constants.CommonConstants.ACCESS_TOKEN;

import java.util.Iterator;
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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.UriComponentsBuilder;

import com.ekaanalytics.common.config.ServerConfiguration;
import com.ekaanalytics.exception.FunctionalException;
import com.ekaanalytics.resource.BaseResource;
import com.ekaanalytics.wrapper.login.AccessTokenResponse;
import com.ekaanalytics.wrapper.login.MobileLoginResponse;
import com.ekaanalytics.wrapper.login.UserInfo;

@RestController
public class LoginResource extends BaseResource{

	private static final Log logger = LogFactory.getLog(LoginResource.class);

	//private WebResponseExceptionTranslator providerExceptionHandler = new DefaultWebResponseExceptionTranslator();

	/**
	 * API Endpoint for mobile login.
	 * 
	 * @param params		- OAUTH_CLIENT_ID, GRANT_TYPE, DEVICE_ID
	 * @param requestBody	- USERNAME, PWD, ACTIVITY_TYPE
	 * @return
	 * @throws FunctionalException
	 */
	@RequestMapping(value = "/login", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MobileLoginResponse> login(HttpServletRequest request, 
			@RequestParam Map<String, String> params) throws FunctionalException  {

		validateParams(params, OAUTH_CLIENT_ID, GRANT_TYPE);
		JSONObject response = new JSONObject();
		try {

			/*
			 *  This part of code is not used as part of 
			 *  the temporary fix to make the mobile application support 
			 *  multiple environments by making user enter the environment URL
			 */

			//  Get tenant short name to form the web server URL

			/*UriComponentsBuilder tenantUriBuilder = UriComponentsBuilder
													.fromHttpUrl(getURIForFetchingTenantDetails(RESOLVE_TENANT_URI))
													.queryParam(ID, requestBody.getUserName());

		String tenantShortName = new JSONObject(httpClient.fireHttpRequest(tenantUriBuilder.build().encode().toUri(), 
																	HttpMethod.GET, 
																	null, 
																	null, 
																	String.class).getBody()).optString(SHORT_NAME);
		if(StringUtils.isEmpty(tenantShortName)){
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
		}*/

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
			//headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

			UriComponentsBuilder loginUriBuilder = UriComponentsBuilder
					.fromHttpUrl(getURIForCACWebRequest(request, CAC_SECURITY_USER_INFO_URI));

			response = new JSONObject(httpClient.fireHttpRequest(loginUriBuilder.build().encode().toUri(), 
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
		}  catch (JSONException e) {
			logger.error(e.getMessage(), e);
			String errorMsg = response.has(MSG) ? response.optString(MSG) : e.getMessage();
			throw new FunctionalException(errorMsg, HttpStatus.BAD_REQUEST);
		}
	}

	private MultiValueMap<String, Object> getMultiValueMapFromJSON(JSONObject fromJson) {
		MultiValueMap<String, Object> jsonAsMap = new LinkedMultiValueMap<>();
		for (Iterator<?> iterator = fromJson.keys(); iterator.hasNext();) {
			String key = (String) iterator.next();
			jsonAsMap.add(key, fromJson.opt(key)); 
		}
		return jsonAsMap;
	}

	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public ResponseEntity<String> logout(HttpServletRequest httpRequest) {

		HttpHeaders headers = getRequestHeaders(httpRequest);
		headers.set(REFRESH_TOKEN_HEADER, httpRequest.getHeader(REFRESH_TOKEN_HEADER));
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
				.fromHttpUrl(getURIForCACWebRequest(httpRequest, 
						CAC_SECURITY_LOGOUT_URI));

		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
				HttpMethod.POST,
				null,
				headers,
				String.class);
	}

	private String constructURIFromPassedShortName(String tenantShortName, String apiURI) {

		return String.format(ServerConfiguration.cacWebServerURL, tenantShortName) + apiURI;
	}


	private String getURIForFetchingTenantDetails(String apiURI) {

		return String.format(ServerConfiguration.cacWebServerURL, STATIC_TENANT_NAME) + apiURI;
	}

/*
	@ExceptionHandler(HttpClientErrorException.class)
	protected ResponseEntity<OAuth2Exception> handleHttpClientErrorException(HttpClientErrorException e) throws Exception {

		if(e.getStatusCode() != HttpStatus.UNAUTHORIZED){
			throw e;
		}
		return providerExceptionHandler.translate(new BadCredentialsException(WRONG_CREDENTIALS));
	}
*/
}
