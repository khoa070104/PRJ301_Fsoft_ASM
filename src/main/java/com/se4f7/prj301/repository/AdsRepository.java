package com.se4f7.prj301.repository;

import com.se4f7.prj301.constants.ErrorMessage;
import com.se4f7.prj301.model.PaginationModel;
import com.se4f7.prj301.model.request.AdsModelRequest;
import com.se4f7.prj301.model.response.AdsModelResponse;
import com.se4f7.prj301.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdsRepository {
    private static final String INSERT_SQL = "INSERT INTO ads (position, width, height, url, images, createdBy, updatedBy) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE ads SET position = ?, width = ?, height = ?, url = ?, images = ?, updatedBy = ? WHERE id = ?";
    private static final String GET_BY_ID_SQL = "SELECT * FROM ads WHERE id = ?";
    private static final String GET_BY_POSITION_SQL = "SELECT * FROM ads WHERE position = ?";
    private static final String DELETE_BY_ID_SQL = "DELETE FROM ads WHERE id = ?";
    private static final String SEARCH_LIST_SQL = "SELECT * FROM ads WHERE position LIKE ? LIMIT ? OFFSET ?";
    private static final String COUNT_BY_POSITION_SQL = "SELECT COUNT(id) AS totalRecord FROM ads WHERE position LIKE ?";

    public boolean create(AdsModelRequest request, String username) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {
            preparedStatement.setString(1, request.getPosition());
            preparedStatement.setString(2, request.getWidth());
            preparedStatement.setString(3, request.getHeight());
            preparedStatement.setString(4, request.getUrl());
            preparedStatement.setString(5, String.join(",", request.getImages()));
            preparedStatement.setString(6, username);
            preparedStatement.setString(7, username);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(ErrorMessage.SQL_ERROR + e.getMessage());
        }
    }

    public boolean update(Long id, AdsModelRequest request, String username) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, request.getPosition());
            preparedStatement.setString(2, request.getWidth());
            preparedStatement.setString(3, request.getHeight());
            preparedStatement.setString(4, request.getUrl());
            preparedStatement.setString(5, String.join(",", request.getImages()));
            preparedStatement.setString(6, username);
            preparedStatement.setLong(7, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(ErrorMessage.SQL_ERROR + e.getMessage());
        }
    }




    public AdsModelResponse getById(Long id) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.isBeforeFirst()) {
                return null;
            }
            AdsModelResponse response = new AdsModelResponse();
            while (rs.next()) {
                response.setId(rs.getLong("id"));
                response.setPosition(rs.getString("position"));
                response.setWidth(rs.getString("width"));
                response.setHeight(rs.getString("height"));
                response.setUrl(rs.getString("url"));
                response.setImages(rs.getString(2).split(","));
                response.setCreatedDate(rs.getString("createdDate"));
                response.setUpdatedDate(rs.getString("updatedDate"));
                response.setStatus(rs.getString("status"));
            }
            return response;
        } catch (Exception e) {
            throw new RuntimeException(ErrorMessage.SQL_ERROR + e.getMessage());
        }
    }

    public AdsModelResponse getByPosition(String position) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_POSITION_SQL)) {
            preparedStatement.setString(1, position);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.isBeforeFirst()) {
                return null;
            }
            AdsModelResponse response = new AdsModelResponse();
            while (rs.next()) {
                response.setId(rs.getLong("id"));
                response.setPosition(rs.getString("position"));
                response.setWidth(rs.getString("width"));
                response.setHeight(rs.getString("height"));
                response.setUrl(rs.getString("url"));
                response.setImages(rs.getString("images").split(","));
                response.setCreatedBy(rs.getString("createdBy"));
                response.setUpdatedBy(rs.getString("updatedBy"));
                response.setCreatedDate(rs.getString("createdDate"));
                response.setUpdatedDate(rs.getString("updatedDate"));
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

    public PaginationModel filterByPosition(int page, int size, String position) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement stmtSelect = connection.prepareStatement(SEARCH_LIST_SQL);
             PreparedStatement stmtCount = connection.prepareStatement(COUNT_BY_POSITION_SQL)) {
            stmtSelect.setString(1, position != null ? "%" + position + "%" : "%%");
            stmtSelect.setInt(2, size);
            stmtSelect.setInt(3, page * size);
            ResultSet rs = stmtSelect.executeQuery();
            List<AdsModelResponse> results = new ArrayList<>();
            while (rs.next()) {
                AdsModelResponse response = new AdsModelResponse();
                response.setId(rs.getLong("id"));
                response.setPosition(rs.getString("position"));
                response.setWidth(rs.getString("width"));
                response.setHeight(rs.getString("height"));
                response.setUrl(rs.getString("url"));
                response.setImages(rs.getString("images").split(","));
                response.setCreatedBy(rs.getString("createdBy"));
                response.setUpdatedBy(rs.getString("updatedBy"));
                response.setCreatedDate(rs.getString("createdDate"));
                response.setUpdatedDate(rs.getString("updatedDate"));
                response.setStatus(rs.getString("status"));
                results.add(response);
            }
            stmtCount.setString(1, position != null ? "%" + position + "%" : "%%");
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
