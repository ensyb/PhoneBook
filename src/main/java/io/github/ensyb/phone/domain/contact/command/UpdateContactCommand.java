package io.github.ensyb.phone.domain.contact.command;

import java.util.List;


import io.github.ensyb.phone.application.commands.Command;
import io.github.ensyb.phone.application.dispatcher.request.Request;
import io.github.ensyb.phone.application.dispatcher.response.Redirect;
import io.github.ensyb.phone.application.dispatcher.response.Response;
import io.github.ensyb.phone.domain.contact.repository.ContactRepository;
import io.github.ensyb.phone.domain.contact.vo.ContactVo;
import io.github.ensyb.phone.domain.user.vo.UserVo;

public class UpdateContactCommand implements Command {

	@Override
	public Response execute(Request request) {
		UserVo user = (UserVo) request.getAttributeFromSession("user");
		@SuppressWarnings("unchecked")
		List<ContactVo> contactList = (List<ContactVo>) request.getAttributeFromSession("searchList");
		ContactVo updatedContact = new ContactVo.ContactVoBuilder()
				.id(Integer.parseInt(request.getParameter("id")))
				.userId(user.userId())
				.name(request.getParameter("name"))
				.phoneNumber(request.getParameter("phonenumber"))
				.description(request.getParameter("description")).build();
		
		for (ContactVo item : contactList) {
			if(item.id() == updatedContact.id())
				contactList.set(contactList.indexOf(item), updatedContact);
		}
		
		ContactRepository repository = new ContactRepository
				.ContactDefaultJdbcRepository(request.useCommonJdbcRepository());
		repository.updateContact(updatedContact);
		
		request.setAttributeInSession("searchList",contactList);
		
		return new Redirect("home.html");
	}

	
}
