package com.eka.mobileserver.coreplatform.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;


public class InsightResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7375090958171693600L;
	
	@JsonProperty("_id")
	private int id;
	private String name;
	private String description;
	private String chartType;
	private List<String> selectedDataviewIds;
	private List<Map<String, Object>> actions;
	private Map<String, Object> contents;
	private boolean isSlicerPresent;
	
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
	public List<Map<String, Object>> getActions() {
		return actions;
	}
	public void setActions(List<Map<String, Object>> actions) {
		this.actions = actions;
	}
	public Map<String, Object> getContents() {
		return contents;
	}
	public void setContents(Map<String, Object> contents) {
		this.contents = contents;
	}
	public boolean isSlicerPresent() {
		return isSlicerPresent;
	}
	public void setIsSlicerPresent(boolean isSlicerPresent) {
		this.isSlicerPresent = isSlicerPresent;
	}
}
