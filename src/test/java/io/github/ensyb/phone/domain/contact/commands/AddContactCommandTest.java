package io.github.ensyb.phone.domain.contact.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import io.github.ensyb.phone.application.dispatcher.request.RequestMock;
import io.github.ensyb.phone.application.dispatcher.request.RequestMock.RequestParametarPair;
import io.github.ensyb.phone.application.dispatcher.response.Response;
import io.github.ensyb.phone.domain.contact.command.AddContactCommand;
import io.github.ensyb.phone.domain.contact.vo.ContactVo;
import io.github.ensyb.phone.domain.user.vo.UserVo;

public class AddContactCommandTest {

	private ContactVo contactTestData;
	private RequestParametarPair[] formParameters;
	private boolean notUseAjax = false;
	
	@Before
	public void setup(){
		this.contactTestData = new ContactVo.ContactVoBuilder()
				.id(1)
				.userId(1).name("ime kontakta")
				.phoneNumber("000-111-222")
				.description("ovo je opis").build();
	
		this.formParameters = new RequestParametarPair[] {
				new RequestParametarPair("name", "ime kontakta"),
				new RequestParametarPair("phonenumber", "000-111-222"),
				new RequestParametarPair("description", "ovo je opis")
		};
	}
	
	
	@Test
	public void testSuccesefulyInsertNewContactIfThereISListToUpdate(){
		AddContactCommand command = new AddContactCommand();
		
		RequestMock mock = new RequestMock(new RequestMock.CommonJdbcMockRepository<ContactVo>(
				this.contactTestData),
				this.notUseAjax,
				this.formParameters);
		mock.setAttributeInSession("user", new UserVo(1, "enso@enso.com", "password"));
		mock.setAttributeInSession("searchList", new ArrayList<ContactVo>());
		
		command.execute(mock);
		
		@SuppressWarnings("unchecked")
		List<ContactVo> contactList = (List<ContactVo>)mock.getAttributeFromSession("searchList");
		assertFalse("not added new contact in list from session",contactList.isEmpty());
	}
	
	@Test
	public void testSuccesefulyInsertNewContactIfNoListToUpdate(){
		AddContactCommand command = new AddContactCommand();
		
		RequestMock mock = new RequestMock(new RequestMock.CommonJdbcMockRepository<ContactVo>(
				this.contactTestData),
				this.notUseAjax,
				this.formParameters);
		mock.setAttributeInSession("user", new UserVo(1, "enso@enso.com", "password"));
		
		Response response = command.execute(mock);
		
		String expectedResponse = "WEB-INF/view/home.jsp";
		assertEquals(expectedResponse, response.consumePath());
		
	}
	
}
