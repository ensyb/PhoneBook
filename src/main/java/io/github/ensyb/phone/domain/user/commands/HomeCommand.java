package io.github.ensyb.phone.domain.user.commands;

import io.github.ensyb.phone.application.commands.Command;
import io.github.ensyb.phone.application.dispatcher.Request;
import io.github.ensyb.phone.application.dispatcher.response.Forward;
import io.github.ensyb.phone.application.dispatcher.response.Response;

public class HomeCommand implements Command {

	@Override
	public Response execute(Request request) {
		return new Forward("home");
	}

}
