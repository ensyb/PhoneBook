package io.github.ensyb.phone.domain.contact.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import io.github.ensyb.phone.application.dispatcher.request.RequestMock;
import io.github.ensyb.phone.application.dispatcher.request.RequestMock.RequestParametarPair;
import io.github.ensyb.phone.domain.contact.command.SearchCommand;
import io.github.ensyb.phone.domain.contact.command.ShowAllContatsCommand;
import io.github.ensyb.phone.domain.contact.vo.ContactVo;
import io.github.ensyb.phone.domain.user.vo.UserVo;

public class SearchContactTest {

	private ContactVo contact;
	private UserVo user;
	private boolean notUseAjax = false;
	
	@Before
	public void setup(){
		this.contact = new ContactVo.ContactVoBuilder()
				.id(1)
				.userId(1).name("ime")
				.phoneNumber("000-111-222")
				.description("opis").build();
		this.user = new UserVo(1,"enso@enso.com" ,"password");
	}
	
	@Test
	public void testIfSearchStringIsEmpty(){
		RequestMock mock = new RequestMock(this.notUseAjax, new RequestParametarPair("searchString",""));
	
		assertEquals("WEB-INF/view/home.jsp", new SearchCommand().execute(mock).consumePath());
		
	}
	
	@Test
	public void searchReturnOneContact(){
		RequestMock mock = new RequestMock(new RequestMock.CommonJdbcMockRepository<>(this.contact),
				this.notUseAjax,
				new RequestParametarPair("searchString","contact"));
		mock.setAttributeInSession("user", this.user);
		
		new SearchCommand().execute(mock);
		
		@SuppressWarnings("unchecked")
		List<ContactVo> contactList = (List<ContactVo>)mock.getAttributeFromSession("searchList");
		
		assertFalse("there is no contact in list", contactList.isEmpty());
		
	}
	
	@Test
	public void testShowAllContacts(){
		RequestMock mock = new RequestMock(new RequestMock.CommonJdbcMockRepository<>(this.contact),
				this.notUseAjax,
				new RequestParametarPair("searchString","contact"));
		mock.setAttributeInSession("user", this.user);
		
		new ShowAllContatsCommand().execute(mock);
		
		@SuppressWarnings("unchecked")
		List<ContactVo> contactList = (List<ContactVo>)mock.getAttributeFromSession("searchList");
		assertFalse("there is no contact in list", contactList.isEmpty());
		
	}
}
