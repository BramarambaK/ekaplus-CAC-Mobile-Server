package com.eka.mobileserver.advanceanalytics.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.core.io.FileSystemResource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class ImageUploadRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	private List<FileSystemResource> files;
	private String user;
	private String processTypes;
	
	public ImageUploadRequest() {
		super();
	}
	
	public ImageUploadRequest(List<FileSystemResource> files) {
		super();
		this.files = files;
	}

	public ImageUploadRequest(List<FileSystemResource> files, String user, String processTypes) {
		super();
		this.files = files;
		this.user = user;
		this.processTypes = processTypes;
	}

	public List<FileSystemResource> getFiles() {
		return files;
	}

	public void setFiles(List<FileSystemResource> files) {
		this.files = files;
	}

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getProcessTypes() {
		return processTypes;
	}
	public void setProcessTypes(String processTypes) {
		this.processTypes = processTypes;
	}
}
