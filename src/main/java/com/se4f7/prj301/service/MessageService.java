package com.se4f7.prj301.service;

import com.se4f7.prj301.model.PaginationModel;
import com.se4f7.prj301.model.response.MessageModelResponse;

public interface MessageService {

    public boolean deleteById(String id);

    public MessageModelResponse getById(String id);

    public PaginationModel filter(String page, String size, String email);
}
