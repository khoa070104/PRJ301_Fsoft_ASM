package com.se4f7.prj301.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.se4f7.prj301.model.ResponseModel;

public class ResponseUtil {

	public static void success(HttpServletResponse resp, Object respData) {
		try {
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json");
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			ResponseModel responseModel = new ResponseModel(true, respData, null);
			String respStr = ow.writeValueAsString(responseModel);
			resp.getWriter().print(respStr);
			resp.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void sendJson(HttpServletResponse resp, Object respData) {
		try {
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json");
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String respStr = ow.writeValueAsString(respData);
			resp.getWriter().print(respStr);
			resp.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void error(HttpServletResponse resp, String errMsg) {
		try {
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json");
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			ResponseModel responseModel = new ResponseModel(false, null, errMsg);
			String respStr = ow.writeValueAsString(responseModel);
			resp.getWriter().print(respStr);
			resp.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
