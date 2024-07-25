package com.se4f7.prj301.model;

public class ResponseModel {

	private boolean isSuccess;
	private Object data;
	private String errMsg;

	public ResponseModel() {
	}

	public ResponseModel(boolean isSuccess, Object data, String errMsg) {
		super();
		this.isSuccess = isSuccess;
		this.data = data;
		this.errMsg = errMsg;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

}
