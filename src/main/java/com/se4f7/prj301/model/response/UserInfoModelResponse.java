package com.se4f7.prj301.model.response;

import com.se4f7.prj301.model.BaseModel;

public class UserInfoModelResponse extends BaseModel{

	private String username;
	// TODO: define more than fields for user info.
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserInfoModelResponse(String username) {
		super();
		this.username = username;
	}

	public UserInfoModelResponse() {
		super();
	}
	
}
