package com.se4f7.prj301.controller.admin.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se4f7.prj301.constants.ErrorMessage;
import com.se4f7.prj301.constants.QueryType;
import com.se4f7.prj301.model.PaginationModel;
import com.se4f7.prj301.model.request.PostsModelRequest;
import com.se4f7.prj301.model.response.PostsModelResponse;
import com.se4f7.prj301.service.PostsService;
import com.se4f7.prj301.service.impl.PostsServiceImpl;
import com.se4f7.prj301.utils.HttpUtil;
import com.se4f7.prj301.utils.ResponseUtil;

@WebServlet(urlPatterns = { "/admin/api/posts" })
// Add @MultipartConfig for enable upload file.
@MultipartConfig
public class PostsController extends HttpServlet {

	private static final long serialVersionUID = -331986167361646886L;

	private PostsService postsService;

	public void init() {
		postsService = new PostsServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// Get JSON payload from request.
			// Parse JSON stringify from request to Java Class.
			PostsModelRequest requestBody = HttpUtil.ofFormData(req.getPart("payload"))
					.toModel(PostsModelRequest.class);
			// Get username from header request.
			String username = req.getAttribute("username").toString();
			// Call service create a new Posts.
			boolean result = postsService.create(requestBody, req.getPart("banner"), username);
			ResponseUtil.success(resp, result);
		} catch (Exception e) {
			ResponseUtil.error(resp, e.getMessage());
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// Get JSON payload from request.
			// Parse JSON stringify from request to Java Class.
			PostsModelRequest requestBody = HttpUtil.ofFormData(req.getPart("payload"))
					.toModel(PostsModelRequest.class);
			// Get username from header request.
			String username = req.getAttribute("username").toString();
			// Call service update Posts.
			boolean result = postsService.update(req.getParameter("id"), requestBody, req.getPart("banner"), username);
			ResponseUtil.success(resp, result);
		} catch (Exception e) {
			ResponseUtil.error(resp, e.getMessage());
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			boolean result = postsService.deleteById(req.getParameter("id"));
			ResponseUtil.success(resp, result);
		} catch (Exception e) {
			ResponseUtil.error(resp, e.getMessage());
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String type = req.getParameter("type");
			switch (type) {
			case QueryType.FILTER:
				String name = req.getParameter("name");
				String page = req.getParameter("page");
				String size = req.getParameter("size");
				PaginationModel results = postsService.filter(page, size, name);
				ResponseUtil.success(resp, results);
				break;
			case QueryType.GET_ONE:
				String id = req.getParameter("id");
				PostsModelResponse result = postsService.getById(id);
				ResponseUtil.success(resp, result);
				break;
			default:
				ResponseUtil.error(resp, ErrorMessage.TYPE_INVALID);
			}
		} catch (Exception e) {
			ResponseUtil.error(resp, e.getMessage());
		}
	}
}
