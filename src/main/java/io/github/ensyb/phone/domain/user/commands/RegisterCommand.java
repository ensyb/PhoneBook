package io.github.ensyb.phone.domain.user.commands;


import io.github.ensyb.phone.application.commands.Command;
import io.github.ensyb.phone.application.controller.Request;
import io.github.ensyb.phone.application.controller.response.Redirect;
import io.github.ensyb.phone.application.controller.response.Response;
import io.github.ensyb.phone.application.controller.response.Write;
import io.github.ensyb.phone.application.repository.CommonJdbcRepository;
import io.github.ensyb.phone.domain.user.repository.UserRepository;
import io.github.ensyb.phone.domain.user.vo.UserVo;

public class RegisterCommand implements Command {

	//ovo popravit -- ovo je samo da vidim dali radi 

	@Override
	public Response execute(Request request) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(isValid(email, password)){	
			UserVo user = new UserVo(email, password);
		
			if(request.isRequestAjax()){
				UserRepository rep = new UserRepository.DefaultJdbcUserRepository(
						new CommonJdbcRepository(request.useDataSource()));
				
				user = rep.insertUser(user);
				if(user.userId() > 0)
					return new Write("success");
			}
			return new Redirect("index.jsp");
			
		}else
			return new Write("ovdje cu napisat zbog cega nije validno");
		
	}
	
	//napravi validator, ovo je privremeno :)
	private boolean isValid(final String email, final String password){
		if(!email.contains("@"))
			return false;
		if(password.length() < 5)
			return false;
		return true;
	}

}
