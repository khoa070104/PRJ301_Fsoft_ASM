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
import com.se4f7.prj301.model.request.WebSettingRequest;
import com.se4f7.prj301.model.response.WebSettingResponse;
import com.se4f7.prj301.service.WebSettingService;
import com.se4f7.prj301.service.impl.WebSettingServiceImpl;
import com.se4f7.prj301.utils.HttpUtil;
import com.se4f7.prj301.utils.ResponseUtil;

@WebServlet(urlPatterns = { "/admin/api/websetting" })
@MultipartConfig
public class WebSettingController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private WebSettingService webSettingService;

    public void init() {
        webSettingService = new WebSettingServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            WebSettingRequest requestBody = HttpUtil.ofFormData(req.getPart("payload"))
                    .toModel(WebSettingRequest.class);
            String username = req.getAttribute("username").toString();
            boolean result = webSettingService.create(requestBody, req.getPart("image"), username);
            ResponseUtil.success(resp, result);
        } catch (Exception e) {
            ResponseUtil.error(resp, e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            WebSettingRequest requestBody = HttpUtil.ofFormData(req.getPart("payload"))
                    .toModel(WebSettingRequest.class);
            String username = req.getAttribute("username").toString();
            boolean result = webSettingService.update(req.getParameter("id"), requestBody, req.getPart("image"), username);
            ResponseUtil.success(resp, result);
        } catch (Exception e) {
            ResponseUtil.error(resp, e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            boolean result = webSettingService.deleteById(req.getParameter("id"));
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
                    String typeFilter = req.getParameter("typeFilter");
                    String page = req.getParameter("page");
                    String size = req.getParameter("size");
                    PaginationModel results = webSettingService.filter(page, size, typeFilter);
                    ResponseUtil.success(resp, results);
                    break;
                case QueryType.GET_ONE:
                    String id = req.getParameter("id");
                    WebSettingResponse result = webSettingService.getById(id);
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
