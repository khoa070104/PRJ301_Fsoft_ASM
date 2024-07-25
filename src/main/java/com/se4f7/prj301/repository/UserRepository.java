package com.se4f7.prj301.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import com.se4f7.prj301.enums.StatusEnum;
import com.se4f7.prj301.model.request.LoginModelRequest;
import com.se4f7.prj301.model.request.RegisterModelRequest;
import com.se4f7.prj301.model.response.LoginModelResponse;
import com.se4f7.prj301.model.response.UserInfoModelResponse;
import com.se4f7.prj301.utils.DBUtil;
import com.se4f7.prj301.utils.JwtTokenUtil;

public class UserRepository {

	private static final String INSERT_USERS_SQL = "INSERT INTO user (username, password, email, role) VALUES (?, ?, ?, ?)";
	private static final String SELECT_BY_USERNAME_OR_EMAIL_AND_STATUS = "select * from user where (username = ? OR email = ?) AND status = ?";
	private static final String SELECT_BY_USERNAME_OR_EMAIL = "select * from user where username = ? or email = ?";

	public boolean createAccount(RegisterModelRequest request) {
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			String hashPassword = BCrypt.hashpw(request.getPassword(), BCrypt.gensalt(12));

			preparedStatement.setString(1, request.getUsername());
			preparedStatement.setString(2, hashPassword);
			preparedStatement.setString(3, request.getEmail());
			preparedStatement.setString(4, request.getUserRole().toString());

			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			DBUtil.printSQLException(e);
		}
		return false;
	}

	public LoginModelResponse validateLogin(LoginModelRequest request) throws SQLException {
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(SELECT_BY_USERNAME_OR_EMAIL_AND_STATUS)) {
			preparedStatement.setString(1, request.getUsername());
			preparedStatement.setString(2, request.getUsername());
			preparedStatement.setString(3, StatusEnum.ACTIVE.toString());

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				// Check password correct.
				if (BCrypt.checkpw(request.getPassword(), rs.getString("password"))) {
					String username = rs.getString("username");
					Long id = rs.getLong("id");
					String jwtToken = JwtTokenUtil.generateToken(id, username);
					return new LoginModelResponse(id, username, jwtToken);
				}
			}
		} catch (SQLException e) {
			DBUtil.printSQLException(e);
		}
		return null;
	}

	public UserInfoModelResponse getByUsernameOrEmail(String username, String email) throws SQLException {
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_USERNAME_OR_EMAIL)) {
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, email);

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			if (!rs.isBeforeFirst()) {
				return null;
			}
			while (rs.next()) {
				return new UserInfoModelResponse(rs.getString("username"));
			}
		} catch (SQLException e) {
			DBUtil.printSQLException(e);
		}
		return null;
	}

}
