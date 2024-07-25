package com.se4f7.prj301.model.request;

public class LoginModelRequest {
	private String username;
	private String password;

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginModelRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public LoginModelRequest() {
		super();
	}

}
