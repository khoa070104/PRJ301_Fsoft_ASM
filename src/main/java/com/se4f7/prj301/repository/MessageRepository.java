package com.se4f7.prj301.repository;

import com.se4f7.prj301.constants.ErrorMessage;
import com.se4f7.prj301.model.PaginationModel;
import com.se4f7.prj301.model.response.MessageModelResponse;
import com.se4f7.prj301.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MessageRepository {
    private static final String DELETE_BY_ID_SQL = "DELETE FROM messages WHERE id = ?";
    private static final String GET_BY_ID_SQL = "SELECT * FROM messages WHERE id = ?";
    private static final String SEARCH_LIST_SQL = "SELECT * FROM messages WHERE email LIKE ? LIMIT ? OFFSET ?";
    private static final String COUNT_BY_EMAIL_SQL = "SELECT COUNT(id) AS totalRecord FROM messages WHERE email LIKE ?";

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

    public MessageModelResponse getById(Long id) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.isBeforeFirst()) {
                return null;
            }
            MessageModelResponse response = new MessageModelResponse();
            while (rs.next()) {
                response.setId(rs.getLong("id"));
                response.setEmail(rs.getString("email"));
                response.setSubject(rs.getString("subject"));
                response.setMessage(rs.getString("message"));
                response.setCreatedDate(rs.getString("createdDate"));
                response.setUpdatedDate(rs.getString("updatedDate"));
                response.setCreatedBy(rs.getString("createdBy"));
                response.setUpdatedBy(rs.getString("updatedBy"));
                response.setStatus(rs.getString("status"));
            }
            return response;
        } catch (Exception e) {
            throw new RuntimeException(ErrorMessage.SQL_ERROR + e.getMessage());
        }
    }

    public PaginationModel filterByEmail(int page, int size, String email) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement stmtSelect = connection.prepareStatement(SEARCH_LIST_SQL);
             PreparedStatement stmtCount = connection.prepareStatement(COUNT_BY_EMAIL_SQL)) {
            stmtSelect.setString(1, email != null ? "%" + email + "%" : "%%");
            stmtSelect.setInt(2, size);
            stmtSelect.setInt(3, page * size);
            ResultSet rs = stmtSelect.executeQuery();
            List<MessageModelResponse> results = new ArrayList<>();
            while (rs.next()) {
                MessageModelResponse response = new MessageModelResponse();
                response.setId(rs.getLong("id"));
                response.setEmail(rs.getString("email"));
                response.setSubject(rs.getString("subject"));
                response.setMessage(rs.getString("message"));
                response.setCreatedDate(rs.getString("createdDate"));
                response.setUpdatedDate(rs.getString("updatedDate"));
                response.setCreatedBy(rs.getString("createdBy"));
                response.setUpdatedBy(rs.getString("updatedBy"));
                response.setStatus(rs.getString("status"));
                results.add(response);
            }
            stmtCount.setString(1, email != null ? "%" + email + "%" : "%%");
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
