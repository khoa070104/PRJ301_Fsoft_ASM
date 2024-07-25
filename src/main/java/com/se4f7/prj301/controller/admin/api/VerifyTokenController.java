package com.se4f7.prj301.controller.admin.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se4f7.prj301.constants.ErrorMessage;
import com.se4f7.prj301.utils.JwtTokenUtil;
import com.se4f7.prj301.utils.ResponseUtil;

@WebServlet("/api/verify-token")
public class VerifyTokenController extends HttpServlet {

	private static final long serialVersionUID = 2860215007883522580L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String accessToken = JwtTokenUtil.getJwtFromRequest(req);
			boolean isValidToken = JwtTokenUtil.validateToken(accessToken);
			if (isValidToken) {
				ResponseUtil.success(resp, true);
			} else {
				ResponseUtil.error(resp, ErrorMessage.TOKEN_INVALID);
			}
		} catch (Exception e) {
			ResponseUtil.error(resp, e.getMessage());
		}
	}
}
