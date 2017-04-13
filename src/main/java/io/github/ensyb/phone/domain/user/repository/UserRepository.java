package io.github.ensyb.phone.domain.user.repository;

import io.github.ensyb.phone.application.repository.CommonJdbcRepository;
import io.github.ensyb.phone.application.repository.mappers.DataInputMapper;
import io.github.ensyb.phone.application.repository.mappers.DataOutputMapper;
import io.github.ensyb.phone.domain.user.vo.UserVo;

public interface UserRepository {

	public UserVo searchUser(int id);
	public UserVo searchUser(String searchString);
	public UserVo insertUser(UserVo user);
	public void updateUser(UserVo user);
	
	public static class DefaultJdbcUserRepository implements UserRepository {
		
		private final CommonJdbcRepository repository;
		private final String selectUserById = "SELECT id,email,password FROM user WHERE id=?";
		private final String selectUserByEmail = "SELECT id,email,password FROM user WHERE email=?";
		private final String insertUser = "INSERT INTO user (id,email,password) VALUES (?,?,?)";
		private final String updateUser = "UPDATE user SET id = ?, email = ?,password = ?  WHERE id = ?";
		
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
		public UserVo searchUser(String email) {
			return repository.querryForObjectPreparedStaement(selectUserByEmail, userInput, email);
		}

		@Override
		public UserVo insertUser(UserVo user) {
			int generatedId = repository.insertObjectReturnKey(insertUser, userOutput.mapper(user));
			return new UserVo(generatedId, user.userEmail(), user.userPassword());
		}

		@Override
		public void updateUser(UserVo user) {
			repository.updateObject(updateUser, userOutput.mapper(user), user.userId());
		}

	}

}
