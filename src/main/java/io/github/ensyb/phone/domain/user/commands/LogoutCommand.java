package io.github.ensyb.phone.domain.user.commands;

import io.github.ensyb.phone.application.commands.Command;
import io.github.ensyb.phone.application.dispatcher.request.Request;
import io.github.ensyb.phone.application.dispatcher.response.Forward;
import io.github.ensyb.phone.application.dispatcher.response.Response;

public class LogoutCommand implements Command{

	@Override
	public Response execute(Request request) {
		request.closeSession();
		return new Forward("index");
	}

}
