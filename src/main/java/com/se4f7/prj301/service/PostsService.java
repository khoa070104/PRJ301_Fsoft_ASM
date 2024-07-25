package com.se4f7.prj301.service;

import javax.servlet.http.Part;

import com.se4f7.prj301.model.PaginationModel;
import com.se4f7.prj301.model.request.PostsModelRequest;
import com.se4f7.prj301.model.response.PostsModelResponse;

public interface PostsService {

	public boolean create(PostsModelRequest request, Part banner, String username);

	public boolean update(String id, PostsModelRequest request, Part file, String username);

	public boolean deleteById(String id);

	public PostsModelResponse getById(String id);

	public PaginationModel filter(String page, String size, String name);

}
