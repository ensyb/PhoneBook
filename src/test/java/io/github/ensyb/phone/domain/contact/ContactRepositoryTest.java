package io.github.ensyb.phone.domain.contact;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.github.ensyb.phone.application.configuration.database.H2Configuration;
import io.github.ensyb.phone.application.connectivity.DataSourceFactory;
import io.github.ensyb.phone.application.repository.CommonJdbcTestingRepository;
import io.github.ensyb.phone.domain.contact.repository.ContactRepository;
import io.github.ensyb.phone.domain.contact.vo.ContactVo;

public class ContactRepositoryTest {

	private BasicDataSource ds;
	private ContactRepository repository;
	private ContactVo contact;

	@Before
	public void setup(){
		DataSourceFactory factory = new DataSourceFactory(new H2Configuration(), "user", "password");
		ds = factory.consumeDataSourceForTest();
		String privremeno = 	"CREATE TABLE IF NOT EXISTS `contact` ("+
								 " `id` integer NOT NULL AUTO_INCREMENT PRIMARY KEY,"+
								 " `userid` integer NOT NULL, "+
								 " `name` varchar(24) NOT NULL,"+
								 " `phonenumber` varchar(16) NOT NULL,"+
								 " `description` varchar(620) NOT NULL,"+")";
		try {
			Connection connection = ds.getConnection();
			connection.setAutoCommit(false);
			PreparedStatement statement = connection.prepareStatement(privremeno);
			statement.executeUpdate();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.repository = new ContactRepository
				.ContactDefaultJdbcRepository(
				new CommonJdbcTestingRepository(this.ds));
		
		contact = new ContactVo
				.ContactVoBuilder()
				.id(1)
				.userId(10)
				.name("fujo")
				.phoneNumber("111-222-333")
				.description("neki moj kontakt").build();
	}
	
	@After
	public void taredown(){
		try {
			Connection connection = ds.getConnection();
			connection.setAutoCommit(false);
			PreparedStatement statement = connection.prepareStatement("DROP TABLE IF EXISTS `contact`;");
			statement.executeUpdate();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testInsertInRepository(){
		repository.insertContact(this.contact);
		List<ContactVo> contacts = repository.searchForContacts(this.contact.userId(), "fuj");
		assertFalse(contacts.isEmpty());
	}
	

	
	@Test
	public void testSearchContact(){
		repository.insertContact(this.contact);
		
		List<ContactVo> selectedVo = repository.searchForContacts(contact.userId(),"fujo");
		
		assertFalse(selectedVo.isEmpty());
	}
	
 	@Test
	public void testSelectFromRepository(){
		repository.insertContact(new ContactVo.ContactVoBuilder()
				.id(3)
				.userId(1)
				.name("contact")
				.phoneNumber("111-111-111")
				.description("das").build());
		
	    ContactVo selectedVo = repository.searchForContact(3);
		
		assertTrue(selectedVo.name().equals("contact"));
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
