package io.github.ensyb.phone.domain.contact.repository;

import io.github.ensyb.phone.application.repository.CommonJdbcRepository;
import io.github.ensyb.phone.application.repository.mappers.DataInputMapper;
import io.github.ensyb.phone.application.repository.mappers.DataOutputMapper;
import io.github.ensyb.phone.domain.contact.vo.ContactVo;

public interface ContactRepository {

	public ContactVo searchForContact(final int id);

	public ContactVo searchForContact(final int userid,final String searchString);

	public ContactVo insertContact(final ContactVo contact);

	public void updateContact(final ContactVo contact);

	public void deleteContact(final ContactVo contact);

	public static class ContactDefaultJdbcRepository implements ContactRepository {

		private final CommonJdbcRepository repository;

		private final String selectContactById = "SELECT id,userid,name, phonenumber, description FROM contact WHERE id=?";
		private final String selectContactBySearchString = "SELECT id,userid,name, phonenumber, description FROM contact WHERE userid =? AND "
				+ "( name LIKE=? OR phonenumber LIKE =? )";
		private final String insertContact = "INSERT INTO contact (id,userid,name,phonenumber,description) VALUES (?,?,?,?,?)";
		private final String deleteContact = "DELETE FROM contact WHERE id = ?";
		private final String updateContact = "UPDATE contact SET id = ?, userid = ?, name= ?, phonenumber= ?, description= ?  WHERE id = ?";
		

		private final DataOutputMapper<ContactVo> contactOutput = (contact) -> {
			return new Object[] { contact.id(), contact.userId(), contact.name(), contact.phoneNumber(),
					contact.description() };
		};

		private final DataInputMapper<ContactVo> contactInput = (resultSet) -> {
			return new ContactVo.ContactVoBuilder().id(resultSet.getInt("id"))
					.userId(resultSet.getInt("userid"))
					.name(resultSet.getString("name"))
					.phoneNumber(resultSet.getString("phonenumber"))
					.description(resultSet.getString("description")).build();
		};

		public ContactDefaultJdbcRepository(CommonJdbcRepository repository) {
			this.repository = repository;
		}

		@Override
		public ContactVo searchForContact(int id) {
			return repository.querryForObjectPreparedStaement(this.selectContactById, this.contactInput, id);
		}

		@Override
		public ContactVo searchForContact(int userid, String searchString) {
			return repository.querryForObjectPreparedStaement(
					this.selectContactBySearchString, this.contactInput, userid,searchString,searchString);
		}

		@Override
		public ContactVo insertContact(ContactVo contact) {
			int contactId = repository.insertObjectReturnKey(this.insertContact, this.contactOutput.mapper(contact)); 
			return new ContactVo.ContactVoBuilder()
					.id(contactId)
					.userId(contact.userId())
					.name(contact.name())
					.phoneNumber(contact.phoneNumber())
					.description(contact.description()).build();
		}

		@Override
		public void updateContact(ContactVo contact) {
			repository.updateObject(this.updateContact, this.contactOutput.mapper(contact), contact.id());

		}

		@Override
		public void deleteContact(ContactVo contact) {
			repository.deleteObject(this.deleteContact, contact.id());
		}

	}

}
