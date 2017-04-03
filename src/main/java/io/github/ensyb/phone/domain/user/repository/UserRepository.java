package io.github.ensyb.phone.domain.user.repository;

import java.util.List;

import io.github.ensyb.phone.application.repository.CommonJdbcRepository;
import io.github.ensyb.phone.application.repository.DataInputMapper;
import io.github.ensyb.phone.application.repository.DataOutputMapper;
import io.github.ensyb.phone.domain.user.vo.UserVo;

public interface UserRepository {

	public UserVo searchUser(int id);
	public UserVo searchUser(String searchString);
	public List<UserVo> searchForMultipleUsers();
	public UserVo insertUser(UserVo user);
	public void updateUser(UserVo user);
	public void deleteUser(UserVo user);
	
	public static class DefaultJdbcUserRepository implements UserRepository {
		
		public final CommonJdbcRepository repository;
		public final String selectUserById = "SELECT id,email,password FROM user WHERE id=?";
		public final String selectUserByEmail = "SELECT id,email,password FROM user WHERE email=?";
		public final String insertUser = "INSERT INTO user (id,email,password) VALUES (?,?,?)";
		
		private DataInputMapper<UserVo> userInput = (resultSet) -> {
			return new UserVo(resultSet.getInt("id"),resultSet.getString("email"), resultSet.getString("password"));
		};
		
		private DataOutputMapper<UserVo> userOutput = (user) -> {
			return new Object[] {user.userId(), user.userEmail(), user.userPassword()};
		};
		
		public DefaultJdbcUserRepository(CommonJdbcRepository repository) {
			this.repository = repository;
		}
		
		@Override
		public UserVo searchUser(int id) {
			return repository.querryForObjectPreparedStaement(selectUserById, userInput, id);
		}

		@Override
		public UserVo searchUser(String searchString) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<UserVo> searchForMultipleUsers() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public UserVo insertUser(UserVo user) {
			int generatedId = repository.insertObjectReturnKey(insertUser, userOutput.mapper(user));
			return user.updateId(generatedId);
		}

		@Override
		public void updateUser(UserVo user) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void deleteUser(UserVo user) {
			// TODO Auto-generated method stub
			
		}
	}

}
