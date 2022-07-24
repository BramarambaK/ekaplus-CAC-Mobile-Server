package com.eka.mobileserver.coreplatform.model;

public class MobileLoginResponse {
	
	private AccessTokenResponse tokenResponse;
	private UserInfo userInfo;
	
	public MobileLoginResponse() {
		super();
	}
	
	public MobileLoginResponse(AccessTokenResponse tokenResponse, UserInfo userInfo) {
		super();
		this.tokenResponse = tokenResponse;
		this.userInfo = userInfo;
	}

	public AccessTokenResponse getTokenResponse() {
		return tokenResponse;
	}

	public void setTokenResponse(AccessTokenResponse tokenResponse) {
		this.tokenResponse = tokenResponse;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
}