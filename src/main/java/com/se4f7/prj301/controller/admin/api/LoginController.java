package com.se4f7.prj301.controller.admin.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se4f7.prj301.model.request.LoginModelRequest;
import com.se4f7.prj301.model.response.LoginModelResponse;
import com.se4f7.prj301.service.AuthService;
import com.se4f7.prj301.service.impl.AuthServiceImpl;
import com.se4f7.prj301.utils.HttpUtil;
import com.se4f7.prj301.utils.ResponseUtil;

@WebServlet("/api/login")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 2860215007883522580L;

	private AuthService authService;

	public void init() {
		authService = new AuthServiceImpl();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			LoginModelRequest request = HttpUtil.of(req.getReader()).toModel(LoginModelRequest.class);
			LoginModelResponse loginInfo = authService.login(request);
			ResponseUtil.success(resp, loginInfo);
		} catch (Exception e) {
			ResponseUtil.error(resp, e.getMessage());
		}
	}
}
