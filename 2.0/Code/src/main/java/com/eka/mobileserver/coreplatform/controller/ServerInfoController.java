package com.eka.mobileserver.coreplatform.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eka.mobileserver.constants.UriConstants;

@RestController
@RequestMapping("${api.context}")
public class ServerInfoController extends BaseController{
	
	@Value("${pollingTimeoutInMillis}")
	private String pollingTimeoutInMillis;
	
	@Value("${contactusURL}")
	private String contactusURL;
	
	@Value("${registrationURL}")
	private String registrationURL;
	
	@Value("${version}")
	private String version;
	
	@Value("${forceUpdate}")
	private Boolean forceUpdate;
	
	@RequestMapping(value = "/web-config", method=RequestMethod.GET, produces=APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getWebServerUrl(HttpServletRequest request) throws JSONException {
		return new ResponseEntity<>(new JSONObject()
											.put("webServerURL", getURIForCACWebRequest(request, "/"))
											.put("pollingTimeInMillis", pollingTimeoutInMillis)
											.put("contactusURL", contactusURL)
											.put("registrationURL", registrationURL)
											.put("versionInfo", new JSONObject()
																		.put("version", version)
																		.put("forceUpdate", forceUpdate))
											.toString(), 
									
									HttpStatus.OK);
	}
	
	@RequestMapping(value = "/web-view-url", method=RequestMethod.GET, produces=APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getWebviewUrl(HttpServletRequest request) throws JSONException {
		return new ResponseEntity<>(new JSONObject().put("url", getURIForCACWebRequest(request, UriConstants.WEB_VIEW_APP)).toString(), HttpStatus.OK);
	}

}
