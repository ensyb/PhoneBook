package io.github.ensyb.phone.application.connectivity;

import org.apache.commons.dbcp2.BasicDataSource;

import io.github.ensyb.phone.application.configuration.database.DatabaseConnectionConfiguration;

public final class DataSourceFactory {

	private final DatabaseConnectionConfiguration databaseUrl;
	private final String username;
	private final String password;
	

	public DataSourceFactory(DatabaseConnectionConfiguration databaseUrl, String username, String password) {
		this.databaseUrl = databaseUrl;
		this.username = username;
		this.password = password;

	}
	
	public BasicDataSource consumeDataSource(final int poolSize){
		return this.setupDS(poolSize);
	}
	
	public BasicDataSource consumeDataSourceForTest(){
		BasicDataSource source = setupDS(1);
		source.setEnableAutoCommitOnReturn(false);
		source.setRollbackOnReturn(true);
		return source;
	}
	
	
	private BasicDataSource setupDS(final int poolSize){
		BasicDataSource source = new BasicDataSource();
		source.setDriverClassName(databaseUrl.driverClassName());
		source.setUrl(databaseUrl.databaseConnectionUrl());
		source.setUsername(username);
		source.setPassword(password);
		
		source.setInitialSize(poolSize);
		return source;
	}

}
