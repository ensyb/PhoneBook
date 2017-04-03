package io.github.ensyb.phone.application;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.github.ensyb.phone.application.controller.ViewModel;

public class ViewModelTest {

	private final String viewFileSufix = ".jsp";
	private final String viewBasePath = "WEB-INF/view/";
	
	@Test
	public void checkIfResolvViewReturnsWorkingPathIfSuffixIsMissing() {
		ViewModel viewModel = new ViewModel("test");
		assertEquals(viewBasePath+"test"+viewFileSufix, viewModel.consumePath());
		
	}

	@Test
	public void checkIfResolvViewReturnsWorkingPathIfSuffixIsProvided() {
		ViewModel viewModel = new ViewModel("test.jsp");
		assertEquals(viewBasePath+"test"+viewFileSufix, viewModel.consumePath());
		
	}
	
	@Test
	public void checkIfResolvViewReturnsWorkingPathIfFullPathIsProvided() {
		ViewModel viewModel = new ViewModel(viewBasePath+"test"+viewFileSufix);
		assertEquals(viewBasePath+"test"+viewFileSufix, viewModel.consumePath());
		
	}
	
	@Test
	public void checkResolvViewForRedirect() {
		String target = "redirected.jsp";
		ViewModel viewModel = new ViewModel(target,true);
		assertEquals(target, viewModel.consumePath());
		
	}
	
	
}
