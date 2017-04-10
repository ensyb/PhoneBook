package io.github.ensyb.phone.application.dispatcher.request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp2.BasicDataSource;

import io.github.ensyb.phone.application.repository.CommonJdbcRepository;
import io.github.ensyb.phone.application.repository.mappers.DataInputMapper;

public class RequestMock implements Request{

	private Map<String, Object> atributeSessionMap;
	private Map<String, Object> atributeRequestMap;
	private List<RequestParametarPair> parametarList;
	private final boolean isAjax;
	private CommonJdbcRepository mockRepository;
	
	public static final class RequestParametarPair{
		public final String key;
		public final String value;
		
		public RequestParametarPair(String key, String value) {
			this.key = key;
			this.value = value;
		}
		public String getKey(){ return this.key; }
		public String getValue(){ return this.value; }
	}
	
	public RequestMock(boolean isAjax, RequestParametarPair...requestParametarList) {
		this.isAjax = isAjax;
		this.atributeSessionMap = new HashMap<>();
		this.atributeRequestMap = new HashMap<>();
		
		this.parametarList = new ArrayList<>();
		for (RequestParametarPair item : requestParametarList) {
			this.parametarList.add(item);
		}
	}
	
	public RequestMock(CommonJdbcRepository mockRepository,boolean isAjax, RequestParametarPair...requestParametarList) {
		this(isAjax, requestParametarList);
		this.mockRepository = mockRepository;
		
	}
	
	@Override
	public boolean isRequestAjax() {
		return this.isAjax;
	}

	@Override
	public BasicDataSource useDataSource() {
		throw new RequestMockException("there is no datasource, not jet supproted for requestMock");
	}

	@Override
	public void setAttribute(String name, Object object) {
		this.atributeRequestMap.put(name, object);
		
	}
	
	@Override
	public CommonJdbcRepository useCommonJdbcRepository() {
		if(this.mockRepository == null)
			throw new RequestMockException("there is no repository");
		return this.mockRepository;
	}
	
	public static class CommonJdbcMockRepository<T> implements CommonJdbcRepository{

		private List<T> objectTestData;
		
		@SafeVarargs
		public CommonJdbcMockRepository(T...testData) {
			this.objectTestData = new ArrayList<>();
			for (T type : testData) {
				this.objectTestData.add(type);
			}
		}
		@SuppressWarnings("unchecked")
		@Override
		public <Type> Type querryForObjectPreparedStaement(String sql, DataInputMapper<Type> mapper,
				Object... preparedStatementParametars) {
			if(this.objectTestData.isEmpty())
				return null;
			return (Type) this.objectTestData.get(objectTestData.size()-1);
		}

		@Override
		@SuppressWarnings("unchecked")
		public <Type> List<Type> querryForListPreparedStaement(String sql, DataInputMapper<Type> mapper,
				Object... preparedStatementParametars) {
			return (List<Type>) this.objectTestData;
		}

		@Override
		public int insertObjectReturnKey(String sql, Object[] outputMapperValues) {
			return 1;
		}
		@Override
		public void insertObject(String sql, Object[] outputMapperValues) {}
		@Override
		public void updateObject(String sql, Object[] outputMapperValues, Object... idObject) {}
		@Override
		public void deleteObject(String sql, Object... idObject) {}
	}

	@Override
	public void setAttributeInSession(String name, Object object) {
		this.atributeSessionMap.put(name, object);
	}

	@Override
	public Object getAttribute(String name) {
		return this.atributeRequestMap.get(name);
	}

	@Override
	public Object getAttributeFromSession(String name) {
		return this.atributeSessionMap.get(name);
	}

	@Override
	public String getParameter(String name) {
		for (RequestParametarPair item : parametarList) {
			if(item.getKey().equals(name))
				return item.getValue();
		}
		throw new RequestMockException("there is no parameter with name "+ name);
	}

	
	@Override
	public void closeSession() {
		this.atributeRequestMap.clear();
		this.atributeSessionMap.clear();
	}

	 private class RequestMockException extends RuntimeException{

		private static final long serialVersionUID = 1L;
		
		public RequestMockException(String message) {
			super(message);
		}
	}




}
