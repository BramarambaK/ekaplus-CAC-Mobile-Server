package com.ekaanalytics.constants;

public class CommonConstants {
	
	// OAuth Related Constants
	public static final String OAUTH_CLIENT_ID = "client_id";
	public static final String GRANT_TYPE = "grant_type";
	
	// Keeping reference as the static tenant resolver short name
	// as ekaadmin.preprod is not https verified yet
	public static final String STATIC_TENANT_NAME = "reference";
	public static final String DEFAULT_CCRI_DOMAIN = "https://ccri.ekaplus.com";
	
	// Http Headers related constants
	public static final String AUTHORIZATION_HEADER = "Authorization";
	public static final String REFRESH_TOKEN_HEADER = "refresh_token";
	public static final String CONTENT_TYPE_HEADER = "Content-Type";
	public static final String ACCEPT_ENCODING_HEADER = "Accept-Encoding";
	public static final String USER_AGENT_HEADER = "User-Agent";
	public static final String DEVICE_ID_HEADER = "Device-Id";
	public static final String TENANT_SHORT_NAME_HEADER = "Tenant-Short-Name";
	public static final String TENANT_FULL_DOMAIN_HEADER = "Tenant-Domain";
	public static final String USER_ID_HEADER = "User-Id";
	public static final String REMOTE_USER_HEADER = "X-Remote-User";
	
	
	public static final String ID = "id";
	public static final String SHORT_NAME = "shortName";
	public static final String USERNAME = "userName";
	public static final String FIRST_NAME = "firstName";
	public static final String CLIENT_NAME = "clientName";
	public static final String EMAIL = "email";
	public static final String USER_TYPE = "userType";
	public static final String TENANT_SHORT_NAME = "tenantShortName";
	public static final String PAZZWORD = "pwd";
	public static final String ACTIVITY_TYPE = "activityType";
	public static final String ACTIVITY_TYPE_CREATE = "create";
	public static final String CLIENT_ID = "clientId";
	public static final String USER_ID = "userID";
	public static final String DEVICE_ID = "deviceId";
	public static final String CATEGORY_ID = "categoryId";
	public static final String APP_TYPE = "apptype";
	public static final String APP_TYPE_BOTH = "both";
	public static final String IS_ADMIN_CONTEXT = "isAdminContext";
	public static final String INCLUDE_SEEDED_APPS = "includeSeededApps";
	public static final String SUCCESS = "success";
	public static final String MSG = "msg";
	public static final String ONLY_FAVOURITE = "onlyFavourite";
	public static final String SEARCH_BY = "searchBy";
	public static final String FILTER = "filter";
	public static final String APP = "app";
	public static final String INSIGHT = "insight";
	
	public static final String REMOTE_METHOD = "remoteMethod";
	public static final String LAST_NAME = "lastName";
	public static final String ACCESS_TOKEN = "access_token";
	public static final String TENANT_INFO = "tenantInfo";
	
	public static final String VERIFY_MFA ="verifymfa";
	public static final String OTP_TOKEN = "otp-token";
	
	private CommonConstants(){
	}
}
