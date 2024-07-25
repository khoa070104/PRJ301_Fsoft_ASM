package com.se4f7.prj301.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.se4f7.prj301.constants.ErrorMessage;
import com.se4f7.prj301.enums.PostsStatusEnum;
import com.se4f7.prj301.model.PaginationModel;
import com.se4f7.prj301.model.request.PostsModelRequest;
import com.se4f7.prj301.model.response.PostsModelResponse;
import com.se4f7.prj301.utils.DBUtil;

public class PostsRepository {
	private static final String INSERT_SQL = "INSERT INTO posts (title, content, status, banner, categoryId, createdBy, updatedBy) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_SQL = "UPDATE posts SET title = ?, content = ?, status = ?, banner = ?, categoryId = ?, updatedBy = ? WHERE id = ?";
	private static final String GET_BY_ID_SQL = "SELECT p.id, p.title, p.content, p.categoryId, p.banner, p.status, p.createdBy, p.updatedBy, p.createdDate, p.updatedDate, c.name as categoryName"
			+ " FROM posts AS p INNER JOIN category AS c ON p.categoryId = c.id WHERE p.id = ?";
	private static final String GET_BY_TITLE_SQL = "SELECT * FROM posts WHERE title = ?";
	private static final String DELETE_BY_ID_SQL = "DELETE FROM posts WHERE id = ?";
	private static final String SEARCH_LIST_SQL = "SELECT p.id, p.title, p.categoryId, p.banner, p.status, p.createdBy, p.updatedBy, p.createdDate, p.updatedDate, c.name as categoryName"
			+ " FROM posts AS p INNER JOIN category AS c ON p.categoryId = c.id"
			+ " WHERE p.title LIKE ? LIMIT ? OFFSET ?";
	private static final String COUNT_BY_NAME_SQL = "SELECT COUNT(p.id) AS totalRecord FROM posts AS p INNER JOIN category AS c ON p.categoryId = c.id WHERE p.title LIKE ?";

	public boolean create(PostsModelRequest request, String username) {
		// Open connection and set SQL query into PreparedStatement.
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {
			// Set parameters.
			preparedStatement.setString(1, request.getTitle());
			preparedStatement.setString(2, request.getContent());
			if (request.getStatus() != null) {
				preparedStatement.setString(3, request.getStatus().toString());
			} else {
				preparedStatement.setString(3, PostsStatusEnum.ACTIVE.toString());
			}
			preparedStatement.setString(4, request.getBanner());
			preparedStatement.setLong(5, request.getCategoryId());
			preparedStatement.setString(6, username);
			preparedStatement.setString(7, username);
			// Show SQL query.
			System.out.println(preparedStatement);
			// Execute query.
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SQL_ERROR + e.getMessage());
		}
	}

	public boolean update(Long id, PostsModelRequest request, String username) {
		// Open connection and set SQL query into PreparedStatement.
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
			// Set parameters.
			preparedStatement.setString(1, request.getTitle());
			preparedStatement.setString(2, request.getContent());
			if (request.getStatus() != null) {
				preparedStatement.setString(3, request.getStatus().toString());
			} else {
				preparedStatement.setString(3, PostsStatusEnum.ACTIVE.toString());
			}
			preparedStatement.setString(4, request.getBanner());
			preparedStatement.setLong(5, request.getCategoryId());
			preparedStatement.setString(6, username);
			preparedStatement.setLong(7, id);
			// Show SQL query.
			System.out.println(preparedStatement);
			// Execute query.
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SQL_ERROR + e.getMessage());
		}
	}

	public PostsModelResponse getById(Long id) {
		// Open connection and set SQL query into PreparedStatement.
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_SQL)) {
			// Set parameters.
			preparedStatement.setLong(1, id);
			// Show SQL query.
			System.out.println(preparedStatement);
			// Execute query.
			ResultSet rs = preparedStatement.executeQuery();
			if (!rs.isBeforeFirst()) {
				return null;
			}
			PostsModelResponse response = new PostsModelResponse();
			while (rs.next()) {
				response.setId(rs.getLong("id"));
				response.setContent(rs.getString("content"));
				response.setTitle(rs.getString("title"));
				response.setStatus(PostsStatusEnum.valueOf(rs.getString("status")));
				response.setBanner(rs.getString("banner"));
				response.setCategoryId(rs.getLong("categoryId"));
				response.setCategoryName(rs.getString("categoryName"));
				response.setCreatedDate(rs.getString("createdDate"));
				response.setUpdatedDate(rs.getString("updatedDate"));
				response.setCreatedBy(rs.getString("createdBy"));
				response.setUpdatedBy(rs.getString("updatedBy"));
			}
			return response;
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SQL_ERROR + e.getMessage());
		}
	}

	public PostsModelResponse getByTitle(String title) {
		// Open connection and set SQL query into PreparedStatement.
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_TITLE_SQL)) {
			// Set parameters.
			preparedStatement.setString(1, title);
			// Show SQL query.
			System.out.println(preparedStatement);
			// Execute query.
			ResultSet rs = preparedStatement.executeQuery();
			if (!rs.isBeforeFirst()) {
				return null;
			}
			PostsModelResponse response = new PostsModelResponse();
			while (rs.next()) {
				response.setId(rs.getLong("id"));
				response.setContent(rs.getString("content"));
				response.setTitle(rs.getString("title"));
				response.setStatus(PostsStatusEnum.valueOf(rs.getString("status")));
				response.setBanner(rs.getString("banner"));
				response.setCategoryId(rs.getLong("categoryId"));
				response.setCreatedDate(rs.getString("createdDate"));
				response.setUpdatedDate(rs.getString("updatedDate"));
				response.setCreatedBy(rs.getString("createdBy"));
				response.setUpdatedBy(rs.getString("updatedBy"));
			}
			return response;
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SQL_ERROR + e.getMessage());
		}
	}

	public boolean deleteById(Long id) {
		// Open connection and set SQL query into PreparedStatement.
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID_SQL)) {
			// Set parameters.
			preparedStatement.setLong(1, id);
			// Show SQL query.
			System.out.println(preparedStatement);
			// Execute query.
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SQL_ERROR + e.getMessage());
		}
	}

	public PaginationModel filterByName(int page, int size, String name) {
		// Open connection and set SQL query into PreparedStatement.
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement stmtSelect = connection.prepareStatement(SEARCH_LIST_SQL);
				PreparedStatement stmtCount = connection.prepareStatement(COUNT_BY_NAME_SQL)) {
			// Set parameters.
			stmtSelect.setString(1, name != null ? "%" + name + "%" : "%%");
			stmtSelect.setInt(2, size);
			stmtSelect.setInt(3, page * size);
			// Show SQL query.
			System.out.println(stmtSelect);
			// Execute query.
			// Select records.
			ResultSet rs = stmtSelect.executeQuery();
			List<PostsModelResponse> results = new ArrayList<PostsModelResponse>();
			while (rs.next()) {
				PostsModelResponse response = new PostsModelResponse();
				response.setId(rs.getLong("id"));
				response.setTitle(rs.getString("title"));
				response.setStatus(PostsStatusEnum.valueOf(rs.getString("status")));
				response.setBanner(rs.getString("banner"));
				response.setCategoryId(rs.getLong("categoryId"));
				response.setCategoryName(rs.getString("categoryName"));
				response.setCreatedDate(rs.getString("createdDate"));
				response.setUpdatedDate(rs.getString("updatedDate"));
				response.setCreatedBy(rs.getString("createdBy"));
				response.setUpdatedBy(rs.getString("updatedBy"));
				results.add(response);
			}

			// Count records;
			stmtCount.setString(1, name != null ? "%" + name + "%" : "%%");
			ResultSet rsCount = stmtCount.executeQuery();
			int totalRecord = 0;
			while (rsCount.next()) {
				totalRecord = rsCount.getInt("totalRecord");
			}
			return new PaginationModel(page, size, totalRecord, results);
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SQL_ERROR + e.getMessage());
		}
	}
}
