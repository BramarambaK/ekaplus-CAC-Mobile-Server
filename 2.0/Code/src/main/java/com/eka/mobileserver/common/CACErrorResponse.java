package com.eka.mobileserver.common;

public class CACErrorResponse {

	private String msg;
	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public CACErrorResponse(String errorMessage) {
		super();
		this.msg = errorMessage;
	}

	@Override
	public String toString() {
		return "{\"msg\":\"" + msg + "\", \"success\":" + success + "}";
	}
}
