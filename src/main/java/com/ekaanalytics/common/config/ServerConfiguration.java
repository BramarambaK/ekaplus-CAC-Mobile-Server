package com.ekaanalytics.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServerConfiguration {
	
	public static String cacWebServerURL;
	public static int pollingTimeoutInMillis;
	public static String contactusURL;
	public static String registrationURL;
	public static String version;
	public static boolean forceUpdate;
	public static short maxMobileNotifications;
	public static String imagesCountForDIApp;
	public static String pushNotificationHost;
	public static String rateLimitingHeaderValue;

	@Value("#{configProperties['cacWebServerUrl']}")
	public void setCacWebServerURL(String cacWebServerURL) {
		
		ServerConfiguration.cacWebServerURL = cacWebServerURL;
	}
	
	@Value("#{configProperties['pollingTimeoutInMillis']}")
	public void setPollingTimeoutInMillis(int pollingTimeoutInMillis) {
		
		ServerConfiguration.pollingTimeoutInMillis = pollingTimeoutInMillis;
	}
	
	@Value("#{configProperties['contactusURL']}")
	public void setContactusURL(String contactusURL) {
		
		ServerConfiguration.contactusURL = contactusURL;
	}
	
	@Value("#{configProperties['registrationURL']}")
	public void setRegistrationURL(String registrationURL) {
		
		ServerConfiguration.registrationURL = registrationURL;
	}
	
	@Value("#{configProperties['version']}")
	public void setVersion(String version) {
		
		ServerConfiguration.version = version;
	}
	
	@Value("#{configProperties['forceUpdate']}")
	public void setForceUpdate(boolean forceUpdate) {
		
		ServerConfiguration.forceUpdate = forceUpdate;
	}
	
	@Value("#{configProperties['maxMobileNotifications']}")
	public void setMaxMobileNotifications(short maxMobileNotifications) {
		
		ServerConfiguration.maxMobileNotifications = maxMobileNotifications;
	}
	
	@Value("#{configProperties['imagesCountForDIApp']}")
	public void setImagesCountForDIApp(String imagesCountForDIApp) {
		
		ServerConfiguration.imagesCountForDIApp = imagesCountForDIApp;
	}
	
	@Value("#{configProperties['pushNotificationHost']}")
	public void setPushNotificationHost(String pushNotificationHost) {
		
		ServerConfiguration.pushNotificationHost = pushNotificationHost;
	}
	@Value("#{configProperties['rateLimitingHeaderValue']}")
	public void setRateLimitingHeaderValue(String rateLimitingHeaderValue) {
		
		ServerConfiguration.rateLimitingHeaderValue = rateLimitingHeaderValue;
	}

}
