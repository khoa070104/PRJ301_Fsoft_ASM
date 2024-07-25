package com.se4f7.prj301.controller.admin.api;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se4f7.prj301.constants.ErrorMessage;
import com.se4f7.prj301.constants.QueryType;
import com.se4f7.prj301.model.PaginationModel;
import com.se4f7.prj301.model.response.MessageModelResponse;
import com.se4f7.prj301.service.MessageService;
import com.se4f7.prj301.service.impl.MessageServiceImpl;
import com.se4f7.prj301.utils.ResponseUtil;

@WebServlet(urlPatterns = { "/admin/api/messages" })
public class MessageController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private MessageService messageService;

    public void init() {
        messageService = new MessageServiceImpl();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            boolean result = messageService.deleteById(req.getParameter("id"));
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
                    String email = req.getParameter("email");
                    String page = req.getParameter("page");
                    String size = req.getParameter("size");
                    PaginationModel results = messageService.filter(page, size, email);
                    ResponseUtil.success(resp, results);
                    break;
                case QueryType.GET_ONE:
                    String id = req.getParameter("id");
                    MessageModelResponse result = messageService.getById(id);
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
