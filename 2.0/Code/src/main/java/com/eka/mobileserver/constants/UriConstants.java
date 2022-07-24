package com.eka.mobileserver.constants;

public class UriConstants {
	
	public static final String RESOLVE_TENANT_URI = "/cac-security/tenantResolver/getUrl";
	public static final String CAC_SECURITY_TOKEN_URI = "/cac-security/api/oauth/token";
	public static final String CAC_WEB_SERVER_LOGIN_URI = "/spring/smartapp/login?type=1";
	public static final String CAC_WEB_SERVER_LOGOUT_URI = "/smartapp/logout";
	public static final String CAC_SECURITY_LOGOUT_URI = "/spring/logout";
	public static final String CAC_SECURITY_USER_INFO_URI = "/cac-security/api/userinfo?filter=all";
	
	public static final String APPS_URI = "/spring/apps/";
	public static final String APP_CATEGORIES_INFO_URI = "/spring/apps/categoryinfo";
	public static final String INSIGHTS_URI = "/spring/smartapp/insights";
	public static final String INSIGHT_DATAVIEWS_NAME_MAP_URI = "/spring/smartapp/insights/%s/dataview-name-map";
	public static final String DATAVIEWS_URI = "/spring/smartapp/dataviews";
	public static final String DATAVIEWS_VISUALIZE = "/spring/smartapp/dataviews/visualize";
	public static final String DATAVIEWS_BASIC_FILTER = "/spring/smartapp/dataviews/basicFilter";
	
	public static final String COLLECTIONS_URI = "/spring/collections";
	public static final String COLLECTIONS_QUICK_EDIT_INFO_URI = COLLECTIONS_URI + "/%s/quick-edit-info";
	public static final String COLLECTIONS_ID_COLUMN_NAME_MAP_URI = COLLECTIONS_URI + "/%s/column-map";
	
	public static final String TOGGLE_FAVOURITE_URI = "/spring/entities/%s/%s/toggle-favourite";
	public static final String BUSINESS_ALERTS_URI = "/spring/smartapp/notificationUnSeenMessages";
	
	public static final String IMAGE_UPLOAD_URI = "/spring/analytics";
	public static final String RESPONSES_BY_COUNT_USER = "/spring/analytics";
	public static final String ANALYTICS_URI = "/spring/analytics/%s";
	public static final String REMAINING_COUNT_URI = "/spring/analytics/count";
	public static final String VALIDATE_IMAGE_NAME_URI = "/spring/analytics/validate";
	
	
	public static final String WEB_VIEW_APP = "/apps/WebviewApp";
	public static final String VALIDATE_CURR_PWD_URI = "/spring/smartapp/validatePassword";
	public static final String SAVE_USER_URI = "/spring/smartapp/saveUser";
	public static final String GET_USER_DATA_URI = "/spring/smartapp/getUserData?userId=";
	public static final String GET_ACTIVE_FARMER_LIST_URI = "/spring/customers/";
	public static final String GET_MY_PROFILE_URI = "/spring/customers/my-profile";
	public static final String PERM_CODES_API = "/spring/permcodes/";
	
	public static final String PUSH_NOTIFICATION_URI = "/device-mappings";	
	public static final String REQUEST_PARAMS = "requestParams";

	public static final String CHANGE_URI = "/spring/smartapp/change-password";

	public static final String FARMER_CONNECT_API_CONTEXT = "/farmer-connect/v2";
	public static final String BIDS_API = FARMER_CONNECT_API_CONTEXT + "/bids";
	public static final String OFFERS_URI = FARMER_CONNECT_API_CONTEXT + "/offers";
	public static final String RATINGS_API = FARMER_CONNECT_API_CONTEXT + "/bids/ratings";
	public static final String FARMER_BIDS_API = FARMER_CONNECT_API_CONTEXT + "/bids/farmer";
	public static final String OFFEROR_BIDS_API = FARMER_CONNECT_API_CONTEXT + "/bids/offeror";
	public static final String OFFEROR_BIDS_CANCEL_API = FARMER_CONNECT_API_CONTEXT + "/bids/offeror/cancel";
	
	public static final String FC_SETTINGS_URI = FARMER_CONNECT_API_CONTEXT + "/general-settings";
	
	public static final String CAC_SECURITY_API = "/cac-security/api/tenant";
	public static final String CAC_MFA_REGENRATEOTP_API = "/cac-security/api/mfa/regenerate-otp";
	
	private UriConstants(){
	}
}
