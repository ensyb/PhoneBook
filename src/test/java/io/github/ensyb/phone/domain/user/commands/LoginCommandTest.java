package io.github.ensyb.phone.domain.user.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.github.ensyb.phone.application.dispatcher.request.RequestMock;
import io.github.ensyb.phone.application.dispatcher.request.RequestMock.RequestParametarPair;
import io.github.ensyb.phone.application.dispatcher.response.Forward;
import io.github.ensyb.phone.application.dispatcher.response.Response;
import io.github.ensyb.phone.domain.user.vo.UserVo;

public class LoginCommandTest {

	
	@Test
	public void testIfRequestIsNotAjax(){
		LoginCommand command = new LoginCommand();
		
		boolean notUseAjax = false;
		Response execute = command.execute(new RequestMock(notUseAjax));
			
		Forward forwardHandler = new Forward("index");
		String expectedPath = forwardHandler.consumePath();
		assertEquals("not use forward to forward request to index.jsp",expectedPath, execute.consumePath());
	}
	
	@Test
	public void testIfSuccessefullyLogedIN(){
		LoginCommand command = new LoginCommand();
		
		boolean useAjax = true;
		RequestMock mock = new RequestMock(new RequestMock.CommonJdbcMockRepository<UserVo>(
				new UserVo(1,"enso@enso.com", "$2a$12$svbA6RB8YCnqaJ8g.54x/uq1nc/16ET11f4JfDaw33nZb7pFXgC4S")),
				
				useAjax , 
				new RequestParametarPair("email","enso@enso.com"),	
				new RequestParametarPair("password","password"));
		
		Response execute = command.execute(mock);
		
		assertEquals("not return response success","success", execute.consumePath());
	}
	
	@Test
	public void testIfEmailIsNotInDatabase(){
		LoginCommand command = new LoginCommand();
		boolean useAjax = true;
		RequestMock mock = new RequestMock(new RequestMock.CommonJdbcMockRepository<UserVo>(), useAjax,
				new RequestParametarPair("email","enso@enso.com"),
				new RequestParametarPair("password","password")); 
	
		Response execute = command.execute(mock);
		String expectedResponse = "korisnik sa enso@enso.com ne postoji";
		assertEquals("not returned email failure",expectedResponse, execute.consumePath());
	}
	
	@Test
	public void testIfPasswordIsNotValid(){
		LoginCommand command = new LoginCommand();
		boolean useAjax = true;
		RequestMock mock = new RequestMock(new RequestMock.CommonJdbcMockRepository<UserVo>(
				new UserVo(1,"enso@enso.com", "$2a$12$svbA6RB8YCnqaJ8g.54x/uq1nc/16ET11f4JfDaw33nZb7pFXgC4S")), useAjax,
				new RequestParametarPair("email","enso@enso.com"),
				new RequestParametarPair("password","pass")); 
		
		Response execute = command.execute(mock);
		String expectedResponse = "netacan password";
		assertEquals("not returned password failure",expectedResponse, execute.consumePath());
	
	}
	
}
