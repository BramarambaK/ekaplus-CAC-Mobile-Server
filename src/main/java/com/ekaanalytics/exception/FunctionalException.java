package com.ekaanalytics.exception;

import org.springframework.http.HttpStatus;

public class FunctionalException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7649316330114145987L;

	private String message;
	private HttpStatus errorStatus;
	
	public FunctionalException(String message, HttpStatus errorStatus) {
		super();
		this.message = message;
		this.errorStatus = errorStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getErrorStatus() {
		return errorStatus;
	}

	public void setErrorStatus(HttpStatus errorStatus) {
		this.errorStatus = errorStatus;
	}

}
