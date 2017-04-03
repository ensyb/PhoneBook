package io.github.ensyb.phone.application.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.github.ensyb.phone.application.controller.ViewModel;

public interface Command {
	
	ViewModel execute(HttpServletRequest request, HttpServletResponse response);
	
}
