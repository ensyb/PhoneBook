package io.github.ensyb.phone.application.commands;

import io.github.ensyb.phone.application.dispatcher.request.Request;
import io.github.ensyb.phone.application.dispatcher.response.Forward;
import io.github.ensyb.phone.application.dispatcher.response.Response;

public class AboutCommand implements Command {

	@Override
	public Response execute(Request request) {
		return new Forward("about.jsp");
	}

}
