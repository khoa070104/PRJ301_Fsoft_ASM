package com.se4f7.prj301.service;

import java.util.Collection;

import javax.servlet.http.Part;

import com.se4f7.prj301.model.PaginationModel;
import com.se4f7.prj301.model.request.AdsModelRequest;
import com.se4f7.prj301.model.response.AdsModelResponse;

public interface AdsService {
    boolean create(AdsModelRequest request, Collection<Part> images, String username);

    boolean update(String id, AdsModelRequest request, Collection<Part> images, String username);

    boolean deleteById(String id);

    AdsModelResponse getById(String id);

    PaginationModel filter(String page, String size, String position);
}
