package com.ekaanalytics.wrapper.coreplatform.dataview;

import java.io.Serializable;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonProperty;


public class DataviewResponse implements Serializable{
	
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
}
