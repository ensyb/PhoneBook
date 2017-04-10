package io.github.ensyb.phone.domain.user.commands;

import org.mindrot.jbcrypt.BCrypt;

import io.github.ensyb.phone.application.commands.Command;
import io.github.ensyb.phone.application.dispatcher.request.Request;
import io.github.ensyb.phone.application.dispatcher.response.Forward;
import io.github.ensyb.phone.application.dispatcher.response.Response;
import io.github.ensyb.phone.application.dispatcher.response.Write;
import io.github.ensyb.phone.domain.user.repository.UserRepository;
import io.github.ensyb.phone.domain.user.vo.UserVo;

public class LoginCommand implements Command{

	//ovo treba popravit!!! if else if else ????
	@Override
	public Response execute(Request request) {
		if(!request.isRequestAjax())
			return new Forward("index");
		
		UserRepository rep = new UserRepository.DefaultJdbcUserRepository(request.useCommonJdbcRepository());
		
		UserVo user = rep.searchUser(request.getParameter("email"));
		if(user == null)
			return new Write("korisnik sa "+ request.getParameter("email")+" ne postoji");			
		else{
			if(!BCrypt.checkpw(request.getParameter("password"), user.userPassword()))
				return new Write("netaèan password");
			else{
				request.setAttributeInSession("user", user);
				return new Write("success");
			}
		}
	}



}
