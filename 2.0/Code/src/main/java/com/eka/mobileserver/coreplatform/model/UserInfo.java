package com.eka.mobileserver.coreplatform.model;

public class UserInfo {

	private Integer userId;
	private String userName;
	private String email;
	private String firstName;
	private String lastName;
	private Integer userType;
	private Integer clientId;
	private String tenantShortName;
	private String clientName;
	
	public UserInfo() {
		super();
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getTenantShortName() {
		return tenantShortName;
	}

	public void setTenantShortName(String tenantShortName) {
		this.tenantShortName = tenantShortName;
	}
}