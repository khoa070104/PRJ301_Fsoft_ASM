package com.se4f7.prj301.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.se4f7.prj301.constants.ErrorMessage;
import com.se4f7.prj301.enums.StatusEnum;
import com.se4f7.prj301.model.PaginationModel;
import com.se4f7.prj301.model.request.WebSettingRequest;
import com.se4f7.prj301.model.response.WebSettingResponse;
import com.se4f7.prj301.utils.DBUtil;

public class WebSettingRepository {
    private static final String INSERT_SQL = "INSERT INTO web_setting (content, type, image, createdBy, updatedBy, status) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE web_setting SET content = ?, type = ?, image = ?, updatedBy = ?, status = ? WHERE id = ?";
    private static final String GET_BY_ID_SQL = "SELECT * FROM web_setting WHERE id = ?";
    private static final String GET_BY_TYPE_SQL = "SELECT * FROM web_setting WHERE type = ?";
    private static final String DELETE_BY_ID_SQL = "DELETE FROM web_setting WHERE id = ?";
    private static final String SEARCH_LIST_SQL = "SELECT * FROM web_setting WHERE type LIKE ? LIMIT ? OFFSET ?";
    private static final String COUNT_BY_TYPE_SQL = "SELECT COUNT(id) AS totalRecord FROM web_setting WHERE type LIKE ?";

    public boolean create(WebSettingRequest request, String username) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {
            preparedStatement.setString(1, request.getContent());
            preparedStatement.setString(2, request.getType());
            preparedStatement.setString(3, request.getImage());
            preparedStatement.setString(4, username);
            preparedStatement.setString(5, username);
            preparedStatement.setString(6, request.getStatus().toString());
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(ErrorMessage.SQL_ERROR + e.getMessage());
        }
    }

    public boolean update(Long id, WebSettingRequest request, String username) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, request.getContent());
            preparedStatement.setString(2, request.getType());
            preparedStatement.setString(3, request.getImage());
            preparedStatement.setString(4, username);
            preparedStatement.setString(5, request.getStatus().toString());
            preparedStatement.setLong(6, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(ErrorMessage.SQL_ERROR + e.getMessage());
        }
    }

    public WebSettingResponse getById(Long id) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.isBeforeFirst()) {
                return null;
            }
            WebSettingResponse response = new WebSettingResponse();
            while (rs.next()) {
                response.setId(rs.getLong("id"));
                response.setContent(rs.getString("content"));
                response.setType(rs.getString("type"));
                response.setImage(rs.getString("image"));
                response.setCreatedDate(rs.getString("createdDate"));
                response.setUpdatedDate(rs.getString("updatedDate"));
                response.setCreatedBy(rs.getString("createdBy"));
                response.setUpdatedBy(rs.getString("updatedBy"));
                response.setStatus(StatusEnum.valueOf(rs.getString("status")));
            }
            return response;
        } catch (Exception e) {
            throw new RuntimeException(ErrorMessage.SQL_ERROR + e.getMessage());
        }
    }

    public WebSettingResponse getByType(String type) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_TYPE_SQL)) {
            preparedStatement.setString(1, type);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.isBeforeFirst()) {
                return null;
            }
            WebSettingResponse response = new WebSettingResponse();
            while (rs.next()) {
                response.setId(rs.getLong("id"));
                response.setContent(rs.getString("content"));
                response.setType(rs.getString("type"));
                response.setImage(rs.getString("image"));
                response.setCreatedDate(rs.getString("createdDate"));
                response.setUpdatedDate(rs.getString("updatedDate"));
                response.setCreatedBy(rs.getString("createdBy"));
                response.setUpdatedBy(rs.getString("updatedBy"));
                response.setStatus(StatusEnum.valueOf(rs.getString("status")));
            }
            return response;
        } catch (Exception e) {
            throw new RuntimeException(ErrorMessage.SQL_ERROR + e.getMessage());
        }
    }

    public boolean deleteById(Long id) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(ErrorMessage.SQL_ERROR + e.getMessage());
        }
    }

    public PaginationModel filterByType(int page, int size, String type) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement stmtSelect = connection.prepareStatement(SEARCH_LIST_SQL);
             PreparedStatement stmtCount = connection.prepareStatement(COUNT_BY_TYPE_SQL)) {
            stmtSelect.setString(1, type != null ? "%" + type + "%" : "%%");
            stmtSelect.setInt(2, size);
            stmtSelect.setInt(3, page * size);
            ResultSet rs = stmtSelect.executeQuery();
            List<WebSettingResponse> results = new ArrayList<>();
            while (rs.next()) {
                WebSettingResponse response = new WebSettingResponse();
                response.setId(rs.getLong("id"));
                response.setContent(rs.getString("content"));
                response.setType(rs.getString("type"));
                response.setImage(rs.getString("image"));
                response.setCreatedDate(rs.getString("createdDate"));
                response.setUpdatedDate(rs.getString("updatedDate"));
                response.setCreatedBy(rs.getString("createdBy"));
                response.setUpdatedBy(rs.getString("updatedBy"));
                response.setStatus(StatusEnum.valueOf(rs.getString("status")));
                results.add(response);
            }
            stmtCount.setString(1, type != null ? "%" + type + "%" : "%%");
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
