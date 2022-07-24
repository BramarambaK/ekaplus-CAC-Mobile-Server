package com.eka.mobileserver.farmerconnect.model;

import java.io.Serializable;

public class BaseOffer implements Serializable{
	
	private static final long serialVersionUID = 5075187500777816878L;
	
	private Double publishedPrice;
	private String product;
	private String offerId;
	private String location;
	private String quality;
	private String cropYear;
	private String priceUnit;
	private String incoTerm;
	private String offerType;
	
	// Non-mandatory fields
	private String username;
	private String quantityUnit;
	private String rolesToPublish;
	private long deliveryFromDateInMillis;
	private long deliveryToDateInMillis;
	private long expiryDateInMillis;
	private String paymentTerms;
	private String packingType;
	private String packingSize;
	/*
	 * Offeror's Specific Fields
	 * Present iff the username field in dataset is mapped
	 */
	private String rating;
	private String offerorName;
	private String offerorMobileNo;
	
	public Double getPublishedPrice() {
		return publishedPrice;
	}
	public void setPublishedPrice(Double publishedPrice) {
		this.publishedPrice = publishedPrice;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getOfferId() {
		return offerId;
	}
	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getCropYear() {
		return cropYear;
	}
	public void setCropYear(String cropYear) {
		this.cropYear = cropYear;
	}
	public String getPriceUnit() {
		return priceUnit;
	}
	public void setPriceUnit(String priceUnit) {
		this.priceUnit = priceUnit;
	}
	public String getIncoTerm() {
		return incoTerm;
	}
	public void setIncoTerm(String incoTerm) {
		this.incoTerm = incoTerm;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getQuantityUnit() {
		return quantityUnit;
	}
	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
	}
	public long getDeliveryFromDateInMillis() {
		return deliveryFromDateInMillis;
	}
	public void setDeliveryFromDateInMillis(long deliveryFromDateInMillis) {
		this.deliveryFromDateInMillis = deliveryFromDateInMillis;
	}
	public long getDeliveryToDateInMillis() {
		return deliveryToDateInMillis;
	}
	public void setDeliveryToDateInMillis(long deliveryToDateInMillis) {
		this.deliveryToDateInMillis = deliveryToDateInMillis;
	}
	public long getExpiryDateInMillis() {
		return expiryDateInMillis;
	}
	public void setExpiryDateInMillis(long expiryDateInMillis) {
		this.expiryDateInMillis = expiryDateInMillis;
	}
	public String getPaymentTerms() {
		return paymentTerms;
	}
	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}
	public String getPackingType() {
		return packingType;
	}
	public void setPackingType(String packingType) {
		this.packingType = packingType;
	}
	public String getPackingSize() {
		return packingSize;
	}
	public void setPackingSize(String packingSize) {
		this.packingSize = packingSize;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getOfferorName() {
		return offerorName;
	}
	public void setOfferorName(String offerorName) {
		this.offerorName = offerorName;
	}
	public String getOfferorMobileNo() {
		return offerorMobileNo;
	}
	public void setOfferorMobileNo(String offerorMobileNo) {
		this.offerorMobileNo = offerorMobileNo;
	}
	public String getOfferType() {
		return offerType;
	}
	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}
	public String getRolesToPublish() {
		return rolesToPublish;
	}
	public void setRolesToPublish(String rolesToPublish) {
		this.rolesToPublish = rolesToPublish;
	}
}
