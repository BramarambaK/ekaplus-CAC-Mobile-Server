package com.ekaanalytics.wrapper.login;

import org.json.JSONException;
import org.json.JSONObject;

public class UserInfo {

	private Integer userId;
	private String userName;
	private String firstName;
	private String pwd;
	private String clientName;
	private String email;
	private Integer clientId;
	private Integer userType;
	private String activityType;
	private String tenantShortName;
	private String lastName;
	
	public UserInfo() {
		super();
	}

	public UserInfo(String userName, String firstName, String clientName, String email, Integer clientId, Integer userId,
			Integer userType, String tenantShortName) {
		super();
		this.userName = userName;
		this.firstName = firstName;
		this.clientName = clientName;
		this.email = email;
		this.clientId = clientId;
		this.userId = userId;
		this.userType = userType;
		this.tenantShortName = tenantShortName;
	}

	
	public UserInfo(Integer userId, String userName, String email, String firstName, String lastName, Integer userType,
			Integer clientId, String tenantShortName, String clientName) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userType = userType;
		this.clientId = clientId;
		this.tenantShortName = tenantShortName;
		this.clientName = clientName;
	}

	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getTenantShortName() {
		return tenantShortName;
	}

	public void setTenantShortName(String tenantShortName) {
		this.tenantShortName = tenantShortName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public JSONObject toJSON() throws JSONException{
		JSONObject jsonValue = new JSONObject();
		jsonValue.put("userName", userName)
				 .put("firstName", firstName)
				 .put("pwd", pwd)
				 .put("activityType", activityType);
		return jsonValue;
	}
	
}
