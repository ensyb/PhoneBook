package io.github.ensyb.phone.domain.contact;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import io.github.ensyb.phone.application.configuration.DatabaseConnectionConfiguration;
import io.github.ensyb.phone.application.connectivity.DataSourceFactory;
import io.github.ensyb.phone.application.repository.CommonJdbcTestingRepository;
import io.github.ensyb.phone.domain.contact.repository.ContactRepository;
import io.github.ensyb.phone.domain.contact.vo.ContactVo;

public class ContactRepositoryTest {

	ContactRepository repository;
	ContactVo contact;
	
	@Before
	public void setup(){
		this.repository = new ContactRepository
				.ContactDefaultJdbcRepository(
				new CommonJdbcTestingRepository(
						new DataSourceFactory(
								new DatabaseConnectionConfiguration.MysqlConfiguration("localhost", "phonebook", 3306), 
								"root", "root").consumeDataSourceForTest()));
		contact = new ContactVo
				.ContactVoBuilder()
				.id(1)
				.userId(10)
				.name("fujo")
				.phoneNumber("111-222-333")
				.description("neki moj kontakt").build();
	}
	
	@Test
	public void testInsertInRepository(){
		ContactVo vo = repository.insertContact(this.contact);
		assertTrue(vo.id() > 0);
	}
	
	@Test
	public void testSelectFromRepository(){
		repository.insertContact(this.contact);
		
	    ContactVo selectedVo = repository.searchForContact(1);
		
		assertTrue(selectedVo.id() > 0);
	}
	
	@Test
	public void testUpdateRepository(){
		ContactVo contact = repository.insertContact(this.contact);
		String newName = "preimenovani";
		contact = new ContactVo.ContactVoBuilder()
				.id(contact.id())
				.userId(contact.userId())
				.name(newName)
				.phoneNumber(contact.phoneNumber())
				.description(contact.description()).build();
		repository.updateContact(contact);
		
		ContactVo updatedContact = repository.searchForContact(contact.id());
		assertEquals(newName, updatedContact.name());
	}
	

	
}
