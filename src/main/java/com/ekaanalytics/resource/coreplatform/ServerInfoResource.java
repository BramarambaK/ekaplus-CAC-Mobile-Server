package com.ekaanalytics.resource.coreplatform;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ekaanalytics.common.config.ServerConfiguration;
import com.ekaanalytics.resource.BaseResource;
import com.ekaanalytics.resource.UriConstants;

@RestController
public class ServerInfoResource extends BaseResource{
	
	@RequestMapping(value = "/web-config", method=RequestMethod.GET, produces=APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getWebServerUrl(HttpServletRequest request) throws JSONException {
		return new ResponseEntity<>(new JSONObject()
											.put("webServerURL", getURIForCACWebRequest(request, "/"))
											.put("pollingTimeInMillis", ServerConfiguration.pollingTimeoutInMillis)
											.put("contactusURL", ServerConfiguration.contactusURL)
											.put("registrationURL", ServerConfiguration.registrationURL)
											.put("versionInfo", new JSONObject()
																		.put("version", ServerConfiguration.version)
																		.put("forceUpdate", ServerConfiguration.forceUpdate))
											.toString(), 
									
									HttpStatus.OK);
	}
	
	@RequestMapping(value = "/web-view-url", method=RequestMethod.GET, produces=APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getWebviewUrl(HttpServletRequest request) throws JSONException {
		return new ResponseEntity<>(new JSONObject().put("url", getURIForCACWebRequest(request, UriConstants.WEB_VIEW_APP)).toString(), HttpStatus.OK);
	}

}
