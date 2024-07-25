package com.se4f7.prj301.service;

import java.sql.SQLException;

import com.se4f7.prj301.model.request.LoginModelRequest;
import com.se4f7.prj301.model.response.LoginModelResponse;

public interface AuthService {

	public LoginModelResponse login(LoginModelRequest request) throws SQLException;

}
