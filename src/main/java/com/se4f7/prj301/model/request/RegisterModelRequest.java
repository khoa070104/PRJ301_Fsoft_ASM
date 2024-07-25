package com.se4f7.prj301.model.request;

import com.se4f7.prj301.enums.UserRoleEnum;

public class RegisterModelRequest {
	private String username;
	private String password;
	private String email;
	private UserRoleEnum userRole;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRoleEnum getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRoleEnum userRole) {
		this.userRole = userRole;
	}

	public RegisterModelRequest(String username, String password, String email, UserRoleEnum userRole) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.userRole = userRole;
	}

	public RegisterModelRequest() {
		super();
	}
}
