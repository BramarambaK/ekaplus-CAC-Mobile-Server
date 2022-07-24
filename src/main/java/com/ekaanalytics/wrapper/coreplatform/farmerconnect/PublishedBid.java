package com.ekaanalytics.wrapper.coreplatform.farmerconnect;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PublishedBid extends BaseBid{
	
	private static final long serialVersionUID = 5459353733236161849L;
	
	private String expiresIn;
	
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
	private Date expiryDate;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
	private Date deliveryFromDate;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
	private Date deliveryToDate;
	private String expiryDateISOString;
	private String deliveryFromDateISOString;
	private String deliveryToDateISOString;
	// Non-mandatory field
	private Double quantity;
	public String getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getExpiryDateISOString() {
		return expiryDateISOString;
	}
	public void setExpiryDateISOString(String expiryDateISOString) {
		this.expiryDateISOString = expiryDateISOString;
	}
	public Date getDeliveryFromDate() {
		return deliveryFromDate;
	}
	public void setDeliveryFromDate(Date deliveryFromDate) {
		this.deliveryFromDate = deliveryFromDate;
	}
	public Date getDeliveryToDate() {
		return deliveryToDate;
	}
	public void setDeliveryToDate(Date deliveryToDate) {
		this.deliveryToDate = deliveryToDate;
	}
	public String getDeliveryFromDateISOString() {
		return deliveryFromDateISOString;
	}
	public void setDeliveryFromDateISOString(String deliveryFromDateISOString) {
		this.deliveryFromDateISOString = deliveryFromDateISOString;
	}
	public String getDeliveryToDateISOString() {
		return deliveryToDateISOString;
	}
	public void setDeliveryToDateISOString(String deliveryToDateISOString) {
		this.deliveryToDateISOString = deliveryToDateISOString;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
}
