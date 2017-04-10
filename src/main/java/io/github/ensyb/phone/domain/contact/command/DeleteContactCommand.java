package io.github.ensyb.phone.domain.contact.command;

import java.util.List;

import io.github.ensyb.phone.application.commands.Command;
import io.github.ensyb.phone.application.dispatcher.request.Request;
import io.github.ensyb.phone.application.dispatcher.response.Redirect;
import io.github.ensyb.phone.application.dispatcher.response.Response;
import io.github.ensyb.phone.application.repository.CommonJdbcRepository;
import io.github.ensyb.phone.domain.contact.repository.ContactRepository;
import io.github.ensyb.phone.domain.contact.vo.ContactVo;

public class DeleteContactCommand implements Command{

	@Override
	public Response execute(Request request) {
		Integer contactId = Integer.parseInt(request.getParameter("id"));
		@SuppressWarnings("unchecked")
		List<ContactVo> contactList = (List<ContactVo>) request.getAttributeFromSession("searchList");
		
		contactList.removeIf(contact -> contact.id() == contactId);
		
		ContactRepository repository = new ContactRepository
				.ContactDefaultJdbcRepository(new CommonJdbcRepository.Repository(request.useDataSource()));
		repository.deleteContact(contactId);
		
		
		return new Redirect("home.html");
	}

}
