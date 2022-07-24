package com.ekaanalytics.wrapper.coreplatform.notification;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class NotificationResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private short totalCount;
	
	private short unseenCount;
	
	@JsonProperty("data")
	private List<BusinessAlert> businessAlerts;
	
	public short getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(short totalCount) {
		this.totalCount = totalCount;
	}
	public short getUnseenCount() {
		return unseenCount;
	}
	public void setUnseenCount(short unseenCount) {
		this.unseenCount = unseenCount;
	}
	public List<BusinessAlert> getBusinessAlerts() {
		return businessAlerts;
	}
	public void setBusinessAlerts(List<BusinessAlert> businessAlerts) {
		this.businessAlerts = businessAlerts;
	}
}
