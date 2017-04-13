package io.github.ensyb.phone.domain.contact.commands;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import io.github.ensyb.phone.application.dispatcher.request.RequestMock;
import io.github.ensyb.phone.application.dispatcher.request.RequestMock.RequestParametarPair;
import io.github.ensyb.phone.domain.contact.command.UpdateContactCommand;
import io.github.ensyb.phone.domain.contact.vo.ContactVo;
import io.github.ensyb.phone.domain.user.vo.UserVo;

public class UpdateContactCommandTest {

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
	public void testUpdateContact(){
		RequestMock mock = new RequestMock(new RequestMock.CommonJdbcMockRepository<>(this.contact),
				this.notUseAjax,
				new RequestParametarPair("searchString","contact"),
				new RequestParametarPair("id", "1"),
				new RequestParametarPair("name", "enso"),
				new RequestParametarPair("phonenumber", "111-222-333"),
				new RequestParametarPair("description", "opis neki"));
		
		mock.setAttributeInSession("user", this.user);
		List<ContactVo> listOfContacts = new ArrayList<>();
		listOfContacts.add(this.contact);
		mock.setAttributeInSession("searchList", listOfContacts);
		
		new UpdateContactCommand().execute(mock);
		
		@SuppressWarnings("unchecked")
		List<ContactVo> updateContacts = (List<ContactVo>) mock.getAttributeFromSession("searchList");
		
		assertTrue("contact is not updated", updateContacts.get(0).name().equals("enso"));
	}
	
	
	
}
