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
import com.se4f7.prj301.model.request.AdsModelRequest;
import com.se4f7.prj301.model.response.AdsModelResponse;
import com.se4f7.prj301.repository.AdsRepository;
import com.se4f7.prj301.service.AdsService;
import com.se4f7.prj301.service.impl.AdsServiceImpl;
import com.se4f7.prj301.utils.HttpUtil;
import com.se4f7.prj301.utils.ResponseUtil;

@WebServlet(urlPatterns = { "/admin/api/ads" })
@MultipartConfig
public class AdsController extends HttpServlet {

    private static final long serialVersionUID = -123456789L;

    private AdsService adsService;

    public void init() {
        adsService = new AdsServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            AdsModelRequest requestBody = HttpUtil.ofFormData(req.getPart("payload")).toModel(AdsModelRequest.class);
            String username = req.getAttribute("username").toString();
            boolean result = adsService.create(requestBody, req.getParts(), username);
            ResponseUtil.success(resp, result);
        } catch (Exception e) {
            ResponseUtil.error(resp, e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            AdsModelRequest requestBody = HttpUtil.ofFormData(req.getPart("payload")).toModel(AdsModelRequest.class);
            String username = req.getAttribute("username").toString();
            AdsRepository adsRepository = new AdsRepository();

            boolean result = adsService.update(req.getParameter("id"), requestBody, req.getParts(), username);
            ResponseUtil.success(resp, result);
        } catch (Exception e) {
            ResponseUtil.error(resp, e.getMessage());
        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            boolean result = adsService.deleteById(req.getParameter("id"));
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
                    String position = req.getParameter("position");
                    String page = req.getParameter("page");
                    String size = req.getParameter("size");
                    PaginationModel results = adsService.filter(page, size, position);
                    ResponseUtil.success(resp, results);
                    break;
                case QueryType.GET_ONE:
                    String id = req.getParameter("id");
                    AdsModelResponse result = adsService.getById(id);
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
