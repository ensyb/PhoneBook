package io.github.ensyb.phone.application.commands;

import io.github.ensyb.phone.application.controller.Request;
import io.github.ensyb.phone.application.controller.response.Forward;
import io.github.ensyb.phone.application.controller.response.Response;

public class NotFoundCommand implements Command{

	@Override
	public Response execute(Request request) {
		//u malom vrtu raste divlji plod
		return new Forward("index.jsp");
	}

}
