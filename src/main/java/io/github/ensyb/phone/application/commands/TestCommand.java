package io.github.ensyb.phone.application.commands;

import io.github.ensyb.phone.application.controller.Request;
import io.github.ensyb.phone.application.controller.response.Forward;
import io.github.ensyb.phone.application.controller.response.Response;
import io.github.ensyb.phone.application.repository.CommonJdbcRepository;
import io.github.ensyb.phone.domain.user.repository.UserRepository;
import io.github.ensyb.phone.domain.user.vo.UserVo;

public class TestCommand implements Command {

	@Override
	public Response execute(Request request) {
		int id = Integer.parseInt(request.getParameter("id"));
		
		UserRepository rep = new UserRepository.DefaultJdbcUserRepository(
				new CommonJdbcRepository(request.useDataSource()));
		UserVo user = rep.searchUser(id);
		
		request.setAttribute("user", user);
		return new Forward("WEB-INF/view/this.jsp");
	}

}
