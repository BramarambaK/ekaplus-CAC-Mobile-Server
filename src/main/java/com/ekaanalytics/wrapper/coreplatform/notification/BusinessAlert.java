package com.ekaanalytics.wrapper.coreplatform.notification;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BusinessAlert implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("Name")
	private String policyName;
	
	@JsonProperty("Status")
	private String status;
	
	@JsonProperty("Run Date")
	private String runDate;
	
	@JsonProperty("Group Name")
	private String groupName;
	
	@JsonProperty("Limit Type")
	private String limitType;
	
	@JsonProperty("Value Type")
	private String valueType;
	
	@JsonProperty("Measure Name")
	private String measureName;
	
	@JsonProperty("Breach Limit")
	private String breachLimit;
	
	@JsonProperty("Threshold Limit")
	private String thresholdLimit;
	
	@JsonProperty("Actuals")
	private String actuals;
	
	@JsonProperty("Dimensions")
	private String dimensions;
	
	@JsonProperty("TIMESTAMP")
	private Timestamp timestamp;
	
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRunDate() {
		return runDate;
	}
	public void setRunDate(String runDate) {
		this.runDate = runDate;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getLimitType() {
		return limitType;
	}
	public void setLimitType(String limitType) {
		this.limitType = limitType;
	}
	public String getValueType() {
		return valueType;
	}
	public void setValueType(String valueType) {
		this.valueType = valueType;
	}
	public String getMeasureName() {
		return measureName;
	}
	public void setMeasureName(String measureName) {
		this.measureName = measureName;
	}
	public String getBreachLimit() {
		return breachLimit;
	}
	public void setBreachLimit(String breachLimit) {
		this.breachLimit = breachLimit;
	}
	public String getThresholdLimit() {
		return thresholdLimit;
	}
	public void setThresholdLimit(String thresholdLimit) {
		this.thresholdLimit = thresholdLimit;
	}
	public String getActuals() {
		return actuals;
	}
	public void setActuals(String actuals) {
		this.actuals = actuals;
	}
	public String getDimensions() {
		return dimensions;
	}
	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
}
