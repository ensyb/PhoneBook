package io.github.ensyb.phone.domain.user.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.github.ensyb.phone.application.dispatcher.request.RequestMock;
import io.github.ensyb.phone.application.dispatcher.request.RequestMock.RequestParametarPair;
import io.github.ensyb.phone.application.dispatcher.response.Response;
import io.github.ensyb.phone.domain.user.commands.RegisterCommand;
import io.github.ensyb.phone.domain.user.vo.UserVo;

public class RegisterCommandTest {

	@Test
	public void testIfRequestIsNotAjax(){
		RegisterCommand command = new RegisterCommand();
		
		Response execute = command.execute(new RequestMock(false));
			
		String expectedPath = "index.html";
		assertEquals("not use redirect to redirect on index.html",expectedPath, execute.consumePath());
	}
	
	@Test
	public void testIfFormParametarsAreValidShouldRegisterNewUser(){
		RegisterCommand command = new RegisterCommand();
		
		boolean useAjax = true;
		RequestMock mock = new RequestMock(new RequestMock.CommonJdbcMockRepository<UserVo>(
				new UserVo(1,"enso@enso.com", "password"),
				new UserVo(2,"dva@enso.com", "password")),
				
				useAjax, 
				new RequestParametarPair("email","enso@enso.com"),	
				new RequestParametarPair("password","password"));
		
		
		Response execute = command.execute(mock);
		String expectedResponse = "success";
		
		assertEquals("not succesefuly registred ",expectedResponse, execute.consumePath());
	}

	@Test
	public void testIfEmailIsNotValid(){
		RegisterCommand command = new RegisterCommand();
		
		boolean useAjax = true;
		RequestMock mock = new RequestMock(useAjax, 
				new RequestParametarPair("email","nijevalidan"),	
				new RequestParametarPair("password","password"));
		
		
		Response execute = command.execute(mock);
		String expectedResponse = "emailValidator";
		
		assertTrue(execute.consumePath().contains(expectedResponse));
	}
	
	@Test
	public void testIfPasswordIsNotValid(){
		RegisterCommand command = new RegisterCommand();
		
		boolean useAjax = true;
		RequestMock mock = new RequestMock(useAjax, 
				new RequestParametarPair("email","email@email.com"),	
				new RequestParametarPair("password","pass"));
		
		
		Response execute = command.execute(mock);
		String expectedResponse = "passwordValidator";
		
		assertTrue(execute.consumePath().contains(expectedResponse));
	}
	
	
}
