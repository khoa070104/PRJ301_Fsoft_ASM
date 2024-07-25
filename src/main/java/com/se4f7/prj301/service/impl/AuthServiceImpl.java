package com.se4f7.prj301.service.impl;

import java.sql.SQLException;

import com.se4f7.prj301.constants.ErrorMessage;
import com.se4f7.prj301.model.request.LoginModelRequest;
import com.se4f7.prj301.model.response.LoginModelResponse;
import com.se4f7.prj301.repository.UserRepository;
import com.se4f7.prj301.service.AuthService;

public class AuthServiceImpl implements AuthService {

	private UserRepository categoryRepository = new UserRepository();

	@Override
	public LoginModelResponse login(LoginModelRequest request) throws SQLException {
		LoginModelResponse validateLogin = categoryRepository.validateLogin(request);
		if (validateLogin == null) {
			throw new RuntimeException(ErrorMessage.LOGIN_FAIL);
		}
		return validateLogin;
	}

}
