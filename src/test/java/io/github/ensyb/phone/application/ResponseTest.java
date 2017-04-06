package io.github.ensyb.phone.application;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.github.ensyb.phone.application.dispatcher.response.Forward;
import io.github.ensyb.phone.application.dispatcher.response.Redirect;
import io.github.ensyb.phone.application.dispatcher.response.Response;

public class ResponseTest {

	private final String viewFileSufix = ".jsp";
	private final String viewBasePath = "WEB-INF/view/";
	
	@Test
	public void checkRequestForwardIfOnlyNameOfResourceIsProvided() {
		Response viewModel = new Forward("test");
		assertEquals(viewBasePath+"test"+viewFileSufix, viewModel.consumePath());
		
	}

	@Test
	public void checkIfRequestForwardRetursWorkingPathIfSuffixIsProvided() {
		Response viewModel = new Forward("test.jsp");
		assertEquals(viewBasePath+"test"+viewFileSufix, viewModel.consumePath());
		
	}
	
	@Test
	public void checkRequestForwardForFullPath() {
		Response viewModel = new Forward(viewBasePath+"test"+viewFileSufix);
		assertEquals(viewBasePath+"test"+viewFileSufix, viewModel.consumePath());
		
	}
	
	@Test
	public void checkResponseForRedirect() {
		String target = "redirected.jsp";
		Response viewModel = new Redirect(target);
		assertEquals(target, viewModel.consumePath());
		
	}
	
	
}
