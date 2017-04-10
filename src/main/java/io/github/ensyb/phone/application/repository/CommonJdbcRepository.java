package io.github.ensyb.phone.application.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.dbcp2.BasicDataSource;

import io.github.ensyb.phone.application.repository.mappers.DataInputMapper;

public interface CommonJdbcRepository {

	public <Type> Type querryForObjectPreparedStaement(String sql, DataInputMapper<Type> mapper,
			Object... preparedStatementParametars);

	public <Type> List<Type> querryForListPreparedStaement(String sql, DataInputMapper<Type> mapper,
			Object... preparedStatementParametars);

	public int insertObjectReturnKey(String sql, Object[] outputMapperValues);

	public void insertObject(String sql, Object[] outputMapperValues);

	public void updateObject(String sql, Object[] outputMapperValues, Object... idObject);

	public void deleteObject(String sql, Object... idObject);

	public static class Repository implements CommonJdbcRepository {
		private final BasicDataSource source;
		private final PreparedStatementFactory prepare;

		public Repository(BasicDataSource datasource) {
			this.source = datasource;
			this.prepare = new PreparedStatementFactory();
		}

		public <Type> Type querryForObjectPreparedStaement(String sql, DataInputMapper<Type> mapper,
				Object... preparedStatementParametars) {

			Type object = null;

			try (Connection connection = source.getConnection();
					PreparedStatement statement = prepare.createPrepareStatement(connection, sql, false,
							preparedStatementParametars);
					ResultSet result = statement.executeQuery()) {
				if (result.next()) {
					object = mapper.mapper(result);
				}
			} catch (SQLException e) {
				throw new CommonRepositoryException(e.getMessage());
			}
			return object;
		}

		public <Type> List<Type> querryForListPreparedStaement(String sql, DataInputMapper<Type> mapper,
				Object... preparedStatementParametars) {

			List<Type> listOfBeans = new ArrayList<>();

			try (Connection connection = source.getConnection();
					PreparedStatement statement = prepare.createPrepareStatement(connection, sql, false,
							preparedStatementParametars);
					ResultSet result = statement.executeQuery()) {
				while (result.next()) {
					listOfBeans.add(mapper.mapper(result));
				}
			} catch (SQLException e) {
				throw new CommonRepositoryException(e.getMessage());
			}
			return listOfBeans;
		}

		public int insertObjectReturnKey(String sql, Object[] outputMapperValues) {
			int generatedKey = 0;
			try (Connection connection = source.getConnection();
					PreparedStatement statement = prepare.createPrepareStatement(connection, sql, true,
							outputMapperValues)) {
				int affectedRows = statement.executeUpdate();
				if (affectedRows == 0)
					throw new CommonRepositoryException("repository insert failed, no rows affected.");
				try (ResultSet res = statement.getGeneratedKeys()) {
					if (res.next()) {
						generatedKey = res.getInt(1);
					} else {
						throw new CommonRepositoryException("repository failed to return generated key");
					}
				}
			} catch (SQLException e) {
				throw new CommonRepositoryException(e.getMessage());
			}
			return generatedKey;
		}

		public void insertObject(String sql, Object[] outputMapperValues) {
			try (Connection connection = source.getConnection();
					PreparedStatement statement = prepare.createPrepareStatement(connection, sql, false,
							outputMapperValues)) {
				int affectedRows = statement.executeUpdate();
				if (affectedRows == 0)
					throw new CommonRepositoryException("repository insert failed, no rows affected.");
			} catch (SQLException e) {
				throw new CommonRepositoryException(e.getMessage());
			}
		}

		public void updateObject(String sql, Object[] outputMapperValues, Object... idObject) {
			Object[] prepareStatementValues = Stream.concat(Arrays.stream(outputMapperValues), Arrays.stream(idObject))
					.toArray(Object[]::new);

//			 Arrays.stream(prepareStatementValues).forEach(System.out::println);

			try (Connection connection = source.getConnection();
					PreparedStatement statement = prepare.createPrepareStatement(connection, sql, false,
							prepareStatementValues)) {
				int affectedRows = statement.executeUpdate();
				if (affectedRows == 0)
					throw new CommonRepositoryException("repository update failed, no rows affected.");
			} catch (SQLException e) {
				throw new CommonRepositoryException(e.getMessage());
			}
		}

		public void deleteObject(String sql, Object... idObject) {

			try (Connection connection = source.getConnection();
					PreparedStatement statement = prepare.createPrepareStatement(connection, sql, false, idObject)) {
				int affectedRows = statement.executeUpdate();
				if (affectedRows == 0)
					throw new CommonRepositoryException("repository update failed, no rows affected.");
			} catch (SQLException e) {
				throw new CommonRepositoryException(e.getMessage());
			}
		}

		public static class PreparedStatementFactory {

			private void populatePrepatedStatement(PreparedStatement statement, Object[] values) throws SQLException {
				for (int i = 0; i < values.length; i++) {
					statement.setObject(i + 1, values[i]);
				}
			}

			public PreparedStatement createPrepareStatement(Connection connection, String sql, boolean generatedKeys,
					Object[] values) throws SQLException {
				PreparedStatement statement;
				if (generatedKeys)
					statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				else
					statement = connection.prepareStatement(sql, Statement.NO_GENERATED_KEYS);

				populatePrepatedStatement(statement, values);
				return statement;
			}
		}
	}

}
