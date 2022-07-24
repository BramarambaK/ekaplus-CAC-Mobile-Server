package com.ekaanalytics.wrapper.coreplatform.farmerconnect;

public class CustomerBid extends BaseBid{

	private static final long serialVersionUID = 4567217438829166521L;
	
	private String refId;
	private String pendingOn;
	private String status;
	// Stored as string to support ',' as '.' separator
	private String quantity;
	
	private long updatedDate;
	private String latestRemarks;
	private String latestOfferorPrice;
	private String latestBidderPrice;
	private String updatedBy;
	private String currentBidRating;
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}
	public String getPendingOn() {
		return pendingOn;
	}
	public void setPendingOn(String pendingOn) {
		this.pendingOn = pendingOn;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public long getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(long updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getLatestRemarks() {
		return latestRemarks;
	}
	public void setLatestRemarks(String latestRemarks) {
		this.latestRemarks = latestRemarks;
	}
	public String getLatestOfferorPrice() {
		return latestOfferorPrice;
	}
	public void setLatestOfferorPrice(String latestOfferorPrice) {
		this.latestOfferorPrice = latestOfferorPrice;
	}
	public String getLatestBidderPrice() {
		return latestBidderPrice;
	}
	public void setLatestBidderPrice(String latestBidderPrice) {
		this.latestBidderPrice = latestBidderPrice;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getCurrentBidRating() {
		return currentBidRating;
	}
	public void setCurrentBidRating(String currentBidRating) {
		this.currentBidRating = currentBidRating;
	}
}
