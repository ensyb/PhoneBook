package io.github.ensyb.phone.application.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.github.ensyb.phone.application.controller.ViewModel;

public class NotFoundCommand implements Command{

	@Override
	public ViewModel execute(HttpServletRequest request, HttpServletResponse response) {
		return new ViewModel("index.jsp",true);
	}


}
