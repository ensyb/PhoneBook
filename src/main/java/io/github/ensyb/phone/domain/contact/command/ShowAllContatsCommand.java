package io.github.ensyb.phone.domain.contact.command;

import java.util.List;

import io.github.ensyb.phone.application.commands.Command;
import io.github.ensyb.phone.application.dispatcher.request.Request;
import io.github.ensyb.phone.application.dispatcher.response.Forward;
import io.github.ensyb.phone.application.dispatcher.response.Response;
import io.github.ensyb.phone.domain.contact.repository.ContactRepository;
import io.github.ensyb.phone.domain.contact.vo.ContactVo;
import io.github.ensyb.phone.domain.user.vo.UserVo;

public class ShowAllContatsCommand implements Command{

	@Override
	public Response execute(Request request) {
		UserVo user = (UserVo) request.getAttributeFromSession("user");
		
		ContactRepository repositoy = new ContactRepository
				.ContactDefaultJdbcRepository(request.useCommonJdbcRepository());
		List<ContactVo> contactList = repositoy.selectAllMyContacts(user.userId());
		
		request.setAttributeInSession("searchList", contactList);
		
		
		return new Forward("home");
	}

}
