package com.se4f7.prj301.service.impl;

import javax.servlet.http.Part;

import com.se4f7.prj301.constants.ErrorMessage;
import com.se4f7.prj301.model.PaginationModel;
import com.se4f7.prj301.model.request.PostsModelRequest;
import com.se4f7.prj301.model.response.PostsModelResponse;
import com.se4f7.prj301.repository.PostsRepository;
import com.se4f7.prj301.service.PostsService;
import com.se4f7.prj301.utils.FileUtil;
import com.se4f7.prj301.utils.StringUtil;

public class PostsServiceImpl implements PostsService {

	private PostsRepository postsRepository = new PostsRepository();

	@Override
	public boolean create(PostsModelRequest request, Part banner, String username) {
		// Validate title is exists.
		PostsModelResponse oldPosts = postsRepository.getByTitle(request.getTitle());
		if (oldPosts != null) {
			throw new RuntimeException(ErrorMessage.NAME_IS_EXISTS);
		}
		// Saving file from request.
		if (banner != null && banner.getSubmittedFileName() != null) {
			// Call function save file and return file name.
			String fileName = FileUtil.saveFile(banner);
			// Set filename saved to Model.
			request.setBanner(fileName);
		}
		// Call repository saving file.
		return postsRepository.create(request, username);
	}

	@Override
	public boolean update(String id, PostsModelRequest request, Part banner, String username) {
		// Parse String to Long.
		Long idNumber = StringUtil.parseLong("Id", id);
		// Get old Posts.
		PostsModelResponse oldPosts = postsRepository.getById(idNumber);
		// If Posts is not exists cannot update so will throw Error.
		if (oldPosts == null) {
			throw new RuntimeException(ErrorMessage.RECORD_NOT_FOUND);
		}
		// Compare is title change.
		if (!request.getTitle().equalsIgnoreCase(oldPosts.getTitle())) {
			// Compare new title with other name in database.
			PostsModelResponse otherPosts = postsRepository.getByTitle(request.getTitle());
			if (otherPosts != null) {
				throw new RuntimeException(ErrorMessage.NAME_IS_EXISTS);
			}
		}
		// Saving file from request.
		if (banner != null && banner.getSubmittedFileName() != null) {
			// Delete old banner -> saving memory.
			FileUtil.removeFile(oldPosts.getBanner());
			// Call function save file and return file name.
			String fileName = FileUtil.saveFile(banner);
			// Set filename saved to Model.
			request.setBanner(fileName);
		} else {
			// If banner not change we don't need replace it.
			// Re-use old name.
			request.setBanner(oldPosts.getBanner());
		}
		// Call repository saving file.
		return postsRepository.update(idNumber, request, username);
	}

	@Override
	public boolean deleteById(String id) {
		Long idNumber = StringUtil.parseLong("Id", id);
		PostsModelResponse oldPosts = postsRepository.getById(idNumber);
		if (oldPosts == null) {
			throw new RuntimeException(ErrorMessage.RECORD_NOT_FOUND);
		}
		if (oldPosts.getBanner() != null) {
			// Delete old banner -> saving memory.
			FileUtil.removeFile(oldPosts.getBanner());
		}
		return postsRepository.deleteById(idNumber);
	}

	@Override
	public PostsModelResponse getById(String id) {
		Long idNumber = StringUtil.parseLong("Id", id);
		PostsModelResponse oldPosts = postsRepository.getById(idNumber);
		if (oldPosts == null) {
			throw new RuntimeException(ErrorMessage.RECORD_NOT_FOUND);
		}
		return oldPosts;
	}

	@Override
	public PaginationModel filter(String page, String size, String name) {
		int pageNumber = StringUtil.parseInt("Page", page);
		int sizeNumber = StringUtil.parseInt("Size", size);
		return postsRepository.filterByName(pageNumber, sizeNumber, name);
	}

}
