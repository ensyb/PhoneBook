package io.github.ensyb.phone.application.commands;

import io.github.ensyb.phone.application.controller.Request;
import io.github.ensyb.phone.application.controller.response.Response;

public interface Command {
	
	Response execute(Request request);
	
}
