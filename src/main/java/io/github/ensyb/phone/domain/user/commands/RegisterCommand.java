package io.github.ensyb.phone.domain.user.commands;

import org.mindrot.jbcrypt.BCrypt;

import io.github.ensyb.phone.application.commands.Command;
import io.github.ensyb.phone.application.dispatcher.Request;
import io.github.ensyb.phone.application.dispatcher.response.Redirect;
import io.github.ensyb.phone.application.dispatcher.response.Response;
import io.github.ensyb.phone.application.dispatcher.response.Write;
import io.github.ensyb.phone.application.repository.CommonJdbcRepository;
import io.github.ensyb.phone.application.validation.ValidationChecker;
import io.github.ensyb.phone.domain.user.repository.UserRepository;
import io.github.ensyb.phone.domain.user.vo.UserVo;

public class RegisterCommand implements Command {

	@Override
	public Response execute(Request request) {
		if (!request.isRequestAjax()) 
			return new Redirect("index.jsp");

		UserVo user = new UserVo(request.getParameter("email"), request.getParameter("password"));
		ValidationChecker checker = new ValidationChecker(user);
		
		if (checker.generateReport().isEmpty()) {
				user = user.updatePassword(BCrypt.hashpw(user.userPassword(), BCrypt.gensalt(12)));
				UserRepository rep = new UserRepository.DefaultJdbcUserRepository(
						new CommonJdbcRepository.Repository(request.useDataSource()));

				user = rep.insertUser(user);
				if (user.userId() > 0)
					return new Write("success");
		}
		return new Write(checker.generateReport());
		
	}

}
