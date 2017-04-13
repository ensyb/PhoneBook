package io.github.ensyb.phone.domain.contact.command;

import java.util.List;

import io.github.ensyb.phone.application.commands.Command;
import io.github.ensyb.phone.application.dispatcher.request.Request;
import io.github.ensyb.phone.application.dispatcher.response.Forward;
import io.github.ensyb.phone.application.dispatcher.response.Response;
import io.github.ensyb.phone.domain.contact.repository.ContactRepository;
import io.github.ensyb.phone.domain.contact.vo.ContactVo;
import io.github.ensyb.phone.domain.user.vo.UserVo;

public class SearchCommand implements Command {

	@Override
	public Response execute(Request request) {
		String searchString = request.getParameter("searchString");
		
		if(searchString == null || searchString.trim().isEmpty())
			return new Forward("home");
		
		UserVo user = (UserVo) request.getAttributeFromSession("user");

		ContactRepository repositoy = new ContactRepository
				.ContactDefaultJdbcRepository(request.useCommonJdbcRepository());
		List<ContactVo> contactList = repositoy.searchForContacts(user.userId(), searchString);
		
		request.setAttributeInSession("searchList", contactList);
		
		return new Forward("home");
	}

}
