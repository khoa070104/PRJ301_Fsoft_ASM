package com.se4f7.prj301.model.response;

public class LoginModelResponse {

	private Long id;
	private String username;
	private String jwtToken;

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LoginModelResponse(Long id, String username, String jwtToken) {
		super();
		this.id = id;
		this.username = username;
		this.jwtToken = jwtToken;
	}

	public LoginModelResponse() {
		super();
	}

}
