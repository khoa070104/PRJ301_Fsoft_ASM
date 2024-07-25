package com.se4f7.prj301.service.impl;

import com.se4f7.prj301.constants.ErrorMessage;
import com.se4f7.prj301.model.PaginationModel;
import com.se4f7.prj301.model.request.CategoryModelRequest;
import com.se4f7.prj301.model.response.CategoryModelResponse;
import com.se4f7.prj301.repository.CategoryRepository;
import com.se4f7.prj301.service.CategoryService;
import com.se4f7.prj301.utils.StringUtil;

public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository = new CategoryRepository();

	@Override
	public boolean create(CategoryModelRequest request, String username) {
		CategoryModelResponse oldCategory = categoryRepository.getByName(request.getName());
		if (oldCategory != null) {
			throw new RuntimeException(ErrorMessage.NAME_IS_EXISTS);
		}
		return categoryRepository.create(request, username);
	}

	@Override
	public boolean update(String id, CategoryModelRequest request, String username) {
		Long idNumber = StringUtil.parseLong("Id", id);
		CategoryModelResponse oldCategory = categoryRepository.getById(idNumber);
		if (oldCategory == null) {
			throw new RuntimeException(ErrorMessage.RECORD_NOT_FOUND);
		}
		if (!request.getName().equalsIgnoreCase(oldCategory.getName())) {
			CategoryModelResponse otherCategory = categoryRepository.getByName(request.getName());
			if (otherCategory != null) {
				throw new RuntimeException(ErrorMessage.NAME_IS_EXISTS);
			}
		}
		return categoryRepository.update(idNumber, request, username);
	}

	@Override
	public boolean deleteById(String id) {
		Long idNumber = StringUtil.parseLong("Id", id);
		CategoryModelResponse oldCategory = categoryRepository.getById(idNumber);
		if (oldCategory == null) {
			throw new RuntimeException(ErrorMessage.RECORD_NOT_FOUND);
		}
		return categoryRepository.deleteById(idNumber);
	}

	@Override
	public CategoryModelResponse getById(String id) {
		Long idNumber = StringUtil.parseLong("Id", id);
		CategoryModelResponse oldCategory = categoryRepository.getById(idNumber);
		if (oldCategory == null) {
			throw new RuntimeException(ErrorMessage.RECORD_NOT_FOUND);
		}
		return oldCategory;
	}

	@Override
	public PaginationModel filter(String page, String size, String name) {
		int pageNumber = StringUtil.parseInt("Page", page);
		int sizeNumber = StringUtil.parseInt("Size", size);
		return categoryRepository.filterByName(pageNumber, sizeNumber, name);
	}

}
