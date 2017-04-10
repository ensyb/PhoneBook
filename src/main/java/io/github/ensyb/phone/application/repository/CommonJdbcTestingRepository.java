package io.github.ensyb.phone.application.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Stream;

import org.apache.commons.dbcp2.BasicDataSource;

import io.github.ensyb.phone.application.repository.CommonJdbcRepository.Repository.PreparedStatementFactory;
import io.github.ensyb.phone.application.repository.mappers.DataInputMapper;

public final class CommonJdbcTestingRepository implements CommonJdbcRepository {

	private final BasicDataSource dataSource;
	private final PreparedStatementFactory preparedStatement;

	private TestDataHolder insertDataHolder = new TestDataHolder();
	private TestDataHolder updateDataHolderForInsert = new TestDataHolder();

	public CommonJdbcTestingRepository(BasicDataSource datasource) {
		this.dataSource = datasource;
		this.preparedStatement = new PreparedStatementFactory();
	}

	final class TestDataHolder {
		private LinkedHashMap<String, Object[]> storage;
		private ReadWriteLock lock;

		{
			this.storage = new LinkedHashMap<>();
			this.lock = new ReentrantReadWriteLock();
		}

		public void insertData(final TestDataPair pair) {
			Lock write = this.lock.writeLock();
			write.lock();
			storage.put(pair.getKey(), pair.getValue());
			write.unlock();
		}

		public Object[] getTestData(final String sql) {
			Lock read = this.lock.readLock();
			try {
				read.lock();
				return this.storage.get(sql);
			} finally {
				read.unlock();
			}
		}

		public TestDataPair getLastInsertedTestData() {
			Lock read = this.lock.readLock();
			try {
				read.lock();
				String key = (String) this.storage.keySet().toArray()[storage.size() - 1];
				Object[] value = this.storage.get(key);
				return new TestDataPair(key, value);
			} finally {
				read.unlock();
			}
		}

		public Map<String, Object[]> getAllData() {
			Lock read = this.lock.readLock();
			try {
				read.lock();
				return new HashMap<>(this.storage);
			} finally {
				read.unlock();
			}
		}

		public boolean isEmpty() {
			Lock read = this.lock.readLock();
			try {
				read.lock();
				return this.storage.isEmpty();
			} finally {
				read.unlock();
			}
		}

		public boolean isHolderContainsMoreThanOne() {
			Lock read = this.lock.readLock();
			try {
				read.lock();
				return this.storage.size() > 1;
			} finally {
				read.unlock();
			}
		}
	}

	final class TestDataPair implements Map.Entry<String, Object[]> {

		private Object[] data;
		private String sql;

		public TestDataPair(final String sql, final Object[] data) {
			this.sql = sql;
			this.data = data;

		}

		@Override
		public String getKey() {
			return this.sql;
		}

		@Override
		public Object[] getValue() {
			return this.data;
		}

		@Override
		public Object[] setValue(Object[] data) {
			Object[] previousValue = this.data;
			this.data = data;
			return previousValue;
		}

	}

	@Override
	public <Type> Type querryForObjectPreparedStaement(String sql, DataInputMapper<Type> mapper,
			Object... preparedStatementParametars) {
		this.throwIfUowIsEmpty("da bi testirali select morate prvo pozvati insert..(...) na repository objektu");
		Type object = null;

		try {
			Connection connection = getConnection();
			this.insertForSelection(connection);
			PreparedStatement statement = preparedStatement.createPrepareStatement(connection, sql, false,
					preparedStatementParametars);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				object = mapper.mapper(result);
			}
			result.close();
			this.closeConnection(connection);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return object;
	}

	@Override
	public <Type> List<Type> querryForListPreparedStaement(String sql, DataInputMapper<Type> mapper,
			Object... preparedStatementParametars) {
		List<Type> listOfBeans = new ArrayList<>();
		this.throwIfUowIsEmpty("da bi testirali select morate prvo pozvati insert..(...) na repository objektu");
		Connection connection = this.getConnection();
		try {
			this.insertForSelection(connection);
			PreparedStatement statement = preparedStatement.createPrepareStatement(connection, sql, false,
					preparedStatementParametars);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				listOfBeans.add(mapper.mapper(result));
			}
			this.closeConnection(connection);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return listOfBeans;
	}

	@Override
	public int insertObjectReturnKey(String sql, Object[] outputMapperValues) {
		this.insertDataHolder.insertData(new TestDataPair(sql, outputMapperValues));
		int generatedKey = 0;
		try {
			Connection connection = getConnection();
			PreparedStatement statement = preparedStatement.createPrepareStatement(connection, sql, true,
					outputMapperValues);
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0)
				throw new CommonJdbcRepositoryForTestException("repository insert failed, no rows affected.");
			ResultSet res = statement.getGeneratedKeys();
			if (res.next()) {
				generatedKey = res.getInt(1);
			} else {
				throw new CommonJdbcRepositoryForTestException("repository failed to return generated key");
			}

			closeConnection(connection);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return generatedKey;
	}

	@Override
	public void insertObject(String sql, Object[] outputMapperValues) {
		this.insertDataHolder.insertData(new TestDataPair(sql, outputMapperValues));
		try {
			Connection connection = getConnection();
			PreparedStatement statement = preparedStatement.createPrepareStatement(connection, sql, false,
					outputMapperValues);
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0)
				throw new CommonJdbcRepositoryForTestException("repository insert failed, no rows affected.");
			closeConnection(connection);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	@Override
	public void updateObject(String sql, Object[] outputMapperValues, Object... idObject) {
		Object[] prepareStatementValues = Stream.concat(Arrays.stream(outputMapperValues), Arrays.stream(idObject))
				.toArray(Object[]::new);
		this.throwIfUowIsEmpty("da bi testirali update prvo morate pozvati insert...(...) u repository objekat");
		try {
			Connection connection = getConnection();
			TestDataPair currentInsertedPair = this.insertDataHolder.getLastInsertedTestData();
			this.insertForUpdateAndDelete(connection);

			PreparedStatement statement = preparedStatement.createPrepareStatement(connection, sql, false,
					prepareStatementValues);
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0)
				throw new CommonJdbcRepositoryForTestException("repository update failed, no rows affected.");

			this.updateDataHolderForInsert.insertData(new TestDataPair(currentInsertedPair.sql, outputMapperValues));
			closeConnection(connection);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	@Override
	public void deleteObject(String sql, Object... idObject) {
		this.throwIfUowIsEmpty("da bi testirali update prvo morate pozvati insert...(...) u repository objekat");
		Connection connection = getConnection();
		try {
			this.insertForUpdateAndDelete(connection);
			PreparedStatement statement = preparedStatement.createPrepareStatement(connection, sql, false, idObject);
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0)
				throw new CommonJdbcRepositoryForTestException("repository update failed, no rows affected.");

		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	private void insertForUpdateAndDelete(Connection connection) {
		if (insertDataHolder.isHolderContainsMoreThanOne())
			this.insertAllPreviousDataInDb(this.insertDataHolder, connection);
		else
			this.insertPairInDb(connection, this.insertDataHolder.getLastInsertedTestData());
	}

	private void insertForSelection(Connection connection) {
		if (insertDataHolder.isHolderContainsMoreThanOne())
			this.insertAllPreviousDataInDb(
					(updateDataHolderForInsert.isEmpty() ? this.insertDataHolder : this.updateDataHolderForInsert),
					connection);
		else
			this.insertPairInDb(connection,
					(this.updateDataHolderForInsert.isEmpty() ? this.insertDataHolder.getLastInsertedTestData()
							: this.updateDataHolderForInsert.getLastInsertedTestData()));
	}

	private Connection getConnection() {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	private void throwIfUowIsEmpty(String throwMessage) {
		if (this.insertDataHolder.isEmpty())
			throw new CommonJdbcRepositoryForTestException(throwMessage);
	}

	private void closeConnection(Connection connection) {
		try {
			connection.rollback();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void insertPairInDb(Connection connection, TestDataPair pair) {
		try {
			PreparedStatement statement = preparedStatement.createPrepareStatement(connection, pair.sql, false,
					pair.data);
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0)
				throw new SQLException("repository insert failed, no rows affected.");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	private void insertAllPreviousDataInDb(TestDataHolder holder, Connection connection) {
		Map<String, Object[]> allData = holder.getAllData();
		for (Entry<String, Object[]> pair : allData.entrySet()) {
			insertPairInDb(connection, new TestDataPair(pair.getKey(), pair.getValue()));
		}
	}

	class CommonJdbcRepositoryForTestException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public CommonJdbcRepositoryForTestException(String message) {
			super(message);
		}
	}

}
