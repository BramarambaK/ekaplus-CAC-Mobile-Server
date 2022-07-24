package com.ekaanalytics.wrapper.coreplatform.app;

import java.io.Serializable;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonProperty;


public class AppResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7375090958171693600L;
	
	@JsonProperty("_id")
	private int id;
	private String name;
	private String description;
	private String apptype;
	private List<String> selectedInsightIds;
	private String isFavourite;
	private String category;
	private boolean isWorkFlowApp;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getApptype() {
		return apptype;
	}
	public void setApptype(String apptype) {
		this.apptype = apptype;
	}
	public List<String> getSelectedInsightIds() {
		return selectedInsightIds;
	}
	public void setSelectedInsightIds(List<String> selectedInsightIds) {
		this.selectedInsightIds = selectedInsightIds;
	}
	public String getIsFavourite() {
		return isFavourite;
	}
	public void setIsFavourite(String isFavourite) {
		this.isFavourite = isFavourite;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public boolean isWorkFlowApp() {
		return isWorkFlowApp;
	}
	public void setIsWorkFlowApp(boolean isWorkFlowApp) {
		this.isWorkFlowApp = isWorkFlowApp;
	}
}
