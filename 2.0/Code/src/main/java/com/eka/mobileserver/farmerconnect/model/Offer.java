package com.eka.mobileserver.farmerconnect.model;

public class Offer extends BaseOffer{
	
	private static final long serialVersionUID = 5459353733236161849L;
	
	private String expiresIn;
	
	// Non-mandatory field
	private Double quantity;
	public String getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
}
