package io.github.ensyb.phone.application.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbcp2.BasicDataSource;

import io.github.ensyb.phone.application.controller.ViewModel;
import io.github.ensyb.phone.application.repository.CommonJdbcRepository;
import io.github.ensyb.phone.domain.user.repository.UserRepository;
import io.github.ensyb.phone.domain.user.vo.UserVo;

public class TestCommand implements Command {

	@Override
	public ViewModel execute(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		
		UserRepository rep = new UserRepository.DefaultJdbcUserRepository(
				new CommonJdbcRepository((BasicDataSource) request.getServletContext().getAttribute("dataSource")));
		UserVo user = rep.searchUser(id);
		
		request.setAttribute("user", user);
		return new ViewModel("WEB-INF/view/this.jsp");
	}

}
