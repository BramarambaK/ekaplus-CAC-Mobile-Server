package com.ekaanalytics.wrapper.coreplatform.search;

import java.util.List;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchListResponse {
	
	private String entityType;
	
	// Common fields
	@JsonProperty("_id")
	private int id;
	private String name;
	private String description;
	
	// App related fields
	private String apptype;
	private List<String> selectedInsightIds;
	private String isFavourite;
	
	// Insight related fields
	private String category;
	private String chartType;
	private List<String> selectedDataviewIds;
	private List<JSONObject> actions;
	private boolean isSlicerPresent;
	
	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
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
	public String getChartType() {
		return chartType;
	}
	public void setChartType(String chartType) {
		this.chartType = chartType;
	}
	public List<String> getSelectedDataviewIds() {
		return selectedDataviewIds;
	}
	public void setSelectedDataviewIds(List<String> selectedDataviewIds) {
		this.selectedDataviewIds = selectedDataviewIds;
	}
	public List<JSONObject> getActions() {
		return actions;
	}
	public void setActions(List<JSONObject> actions) {
		this.actions = actions;
	}
	public boolean isSlicerPresent() {
		return isSlicerPresent;
	}
	public void setSlicerPresent(boolean isSlicerPresent) {
		this.isSlicerPresent = isSlicerPresent;
	}
}
