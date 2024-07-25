package com.se4f7.prj301.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.se4f7.prj301.constants.ErrorMessage;
import com.se4f7.prj301.enums.StatusEnum;
import com.se4f7.prj301.model.PaginationModel;
import com.se4f7.prj301.model.request.CategoryModelRequest;
import com.se4f7.prj301.model.response.CategoryModelResponse;
import com.se4f7.prj301.utils.DBUtil;

public class CategoryRepository {
	private static final String INSERT_SQL = "INSERT INTO category (name, description, createdBy, updatedBy) VALUES (?, ?, ?, ?)";
	private static final String UPDATE_SQL = "UPDATE category SET name = ?, description = ?, updatedBy = ? WHERE id = ?";
	private static final String GET_BY_ID_SQL = "SELECT * FROM category WHERE id = ?";
	private static final String GET_BY_NAME_SQL = "SELECT * FROM category WHERE name = ?";
	private static final String DELETE_BY_ID_SQL = "DELETE FROM category WHERE id = ?";
	private static final String SEARCH_LIST_SQL = "SELECT * FROM category WHERE name LIKE ? LIMIT ? OFFSET ?";
	private static final String COUNT_BY_NAME_SQL = "SELECT COUNT(id) AS totalRecord FROM category WHERE name LIKE ?";

	public boolean create(CategoryModelRequest request, String username) {
		// Open connection and set SQL query into PreparedStatement.
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {
			// Set parameters.
			preparedStatement.setString(1, request.getName());
			preparedStatement.setString(2, request.getDescription());
			preparedStatement.setString(3, username);
			preparedStatement.setString(4, username);
			// Show SQL query.
			System.out.println(preparedStatement);
			// Execute query.
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SQL_ERROR + e.getMessage());
		}
	}

	public boolean update(Long id, CategoryModelRequest request, String username) {
		// Open connection and set SQL query into PreparedStatement.
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
			// Set parameters.
			preparedStatement.setString(1, request.getName());
			preparedStatement.setString(2, request.getDescription());
			preparedStatement.setString(3, username);
			preparedStatement.setLong(4, id);
			// Show SQL query.
			System.out.println(preparedStatement);
			// Execute query.
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SQL_ERROR + e.getMessage());
		}
	}

	public CategoryModelResponse getById(Long id) {
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
			CategoryModelResponse response = new CategoryModelResponse();
			while (rs.next()) {
				response.setId(rs.getLong("id"));
				response.setName(rs.getString("name"));
				response.setDescription(rs.getString("description"));
				response.setStatus(StatusEnum.valueOf(rs.getString("status")));
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

	public CategoryModelResponse getByName(String name) {
		// Open connection and set SQL query into PreparedStatement.
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_NAME_SQL)) {
			// Set parameters.
			preparedStatement.setNString(1, name);
			// Show SQL query.
			System.out.println(preparedStatement);
			// Execute query.
			ResultSet rs = preparedStatement.executeQuery();
			if (!rs.isBeforeFirst()) {
				return null;
			}
			CategoryModelResponse response = new CategoryModelResponse();
			while (rs.next()) {
				response.setId(rs.getLong("id"));
				response.setName(rs.getString("name"));
				response.setDescription(rs.getString("description"));
				response.setStatus(StatusEnum.valueOf(rs.getString("status")));
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
			List<CategoryModelResponse> results = new ArrayList<CategoryModelResponse>();
			while (rs.next()) {
				CategoryModelResponse response = new CategoryModelResponse();
				response.setId(rs.getLong("id"));
				response.setName(rs.getString("name"));
				response.setDescription(rs.getString("description"));
				response.setStatus(StatusEnum.valueOf(rs.getString("status")));
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
