package com.se4f7.prj301;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.se4f7.prj301.enums.UserRoleEnum;
import com.se4f7.prj301.model.request.RegisterModelRequest;
import com.se4f7.prj301.model.response.UserInfoModelResponse;
import com.se4f7.prj301.repository.UserRepository;

public class ServletInitializer implements ServletContextListener {

	UserRepository userRepository = new UserRepository();

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			UserInfoModelResponse userInfo = userRepository.getByUsernameOrEmail("admin", "admin@study.com");
			if (userInfo == null) {
				userRepository.createAccount(
						new RegisterModelRequest("admin", "admin", "admin@study.com", UserRoleEnum.ADMIN));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
