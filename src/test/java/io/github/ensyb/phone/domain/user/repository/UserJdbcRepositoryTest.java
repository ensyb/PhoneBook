package io.github.ensyb.phone.domain.user.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.github.ensyb.phone.application.configuration.database.H2Configuration;
import io.github.ensyb.phone.application.connectivity.DataSourceFactory;
import io.github.ensyb.phone.application.repository.CommonJdbcTestingRepository;
import io.github.ensyb.phone.domain.user.vo.UserVo;

public class UserJdbcRepositoryTest {

	private BasicDataSource datasource;
	private UserRepository repository;
	@Before 
	public void setup(){
		DataSourceFactory dsFactory = new DataSourceFactory(new H2Configuration(), "user", "password");
		datasource = dsFactory.consumeDataSourceForTest();
		String userTable = "CREATE TABLE IF NOT EXISTS `user` ("+
						  "`id` integer NOT NULL AUTO_INCREMENT PRIMARY KEY,"+
						  "`email` varchar(512) NOT NULL,"+
						  "`password` varchar(512) NOT NULL, )";
	
		try{
			Connection connection = datasource.getConnection();
			PreparedStatement statement = connection.prepareStatement(userTable);
			statement.executeUpdate();
			connection.close();
		}catch(SQLException e){
			System.out.println(e);
		}
		this.repository = new UserRepository.DefaultJdbcUserRepository(new CommonJdbcTestingRepository(this.datasource));
	}
	
	@After
	public void taredown(){
		try {
			Connection connection = this.datasource.getConnection();
			PreparedStatement statement = connection.prepareStatement("DROP TABLE IF EXISTS `user`;");
			statement.executeUpdate();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInsertInUserRepository(){
		repository.insertUser(new UserVo(1,"enso@enso.com", "password"));
		UserVo user = repository.searchUser(1);
		assertTrue("user is not inserted no returned generated id ",user.userEmail().equals("enso@enso.com"));
	}
	
	@Test
	public void testUpdateInUserRepository(){
		repository.insertUser(new UserVo(1,"enso@enso.com", "password"));
		UserVo userBeforeUpdate = repository.searchUser(1);
		repository.updateUser(new UserVo(userBeforeUpdate.userId(), "novi@mail.com", "password"));
		UserVo userAfterUpdate = repository.searchUser(1);
		assertFalse("user is not updated ",userBeforeUpdate.userEmail().equals(userAfterUpdate.userEmail()));
	}
}
