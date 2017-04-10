package io.github.ensyb.phone.application.commands;

import io.github.ensyb.phone.application.dispatcher.request.Request;
import io.github.ensyb.phone.application.dispatcher.response.Response;

public interface Command {
	
	Response execute(Request request);
	
}
