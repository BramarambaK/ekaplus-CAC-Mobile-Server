package com.eka.mobileserver.coreplatform.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccessTokenResponse {

	@JsonProperty("auth2AccessToken")
	private Map<String, Object> tokenInfo;
	private int sessionTimeoutInSeconds;
	
	public AccessTokenResponse() {
		super();
	}

	public Map<String, Object> getTokenInfo() {
		return tokenInfo;
	}

	public void setTokenInfo(Map<String, Object> tokenInfo) {
		this.tokenInfo = tokenInfo;
	}

	public int getSessionTimeoutInSeconds() {
		return sessionTimeoutInSeconds;
	}

	public void setSessionTimeoutInSeconds(int sessionTimeoutInSeconds) {
		this.sessionTimeoutInSeconds = sessionTimeoutInSeconds;
	}
}