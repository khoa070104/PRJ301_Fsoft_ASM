package com.se4f7.prj301.service;

import javax.servlet.http.Part;

import com.se4f7.prj301.model.PaginationModel;
import com.se4f7.prj301.model.request.WebSettingRequest;
import com.se4f7.prj301.model.response.WebSettingResponse;

public interface WebSettingService {

    public boolean create(WebSettingRequest request, Part image, String username);

    public boolean update(String id, WebSettingRequest request, Part image, String username);

    public boolean deleteById(String id);

    public WebSettingResponse getById(String id);

    public PaginationModel filter(String page, String size, String typeFilter);
}
