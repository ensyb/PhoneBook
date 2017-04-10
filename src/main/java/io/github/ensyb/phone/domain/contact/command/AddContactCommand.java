package io.github.ensyb.phone.domain.contact.command;

import java.util.List;

import io.github.ensyb.phone.application.commands.Command;
import io.github.ensyb.phone.application.dispatcher.request.Request;
import io.github.ensyb.phone.application.dispatcher.response.Forward;
import io.github.ensyb.phone.application.dispatcher.response.Response;
import io.github.ensyb.phone.application.repository.CommonJdbcRepository;
import io.github.ensyb.phone.domain.contact.repository.ContactRepository;
import io.github.ensyb.phone.domain.contact.vo.ContactVo;
import io.github.ensyb.phone.domain.user.vo.UserVo;

public class AddContactCommand implements Command{

	@Override
	public Response execute(Request request) {
		UserVo userIs = (UserVo)request.getAttributeFromSession("user");
		
		ContactVo contact = new ContactVo.ContactVoBuilder()
				.userId(userIs.userId())
				.name(request.getParameter("name"))
				.phoneNumber(request.getParameter("phonenumber"))
				.description(request.getParameter("description")).build();
		
		ContactRepository repository = new ContactRepository
				.ContactDefaultJdbcRepository(new CommonJdbcRepository.Repository(request.useDataSource()));
		
		ContactVo inserted = repository.insertContact(contact);
		@SuppressWarnings("unchecked")
		List<ContactVo> contactList = (List<ContactVo>) request.getAttributeFromSession("searchList");
		if(contactList != null){
			contactList.add(inserted);
			request.setAttributeInSession("searchList", contactList);
		}
		return new Forward("home");
	}


}
