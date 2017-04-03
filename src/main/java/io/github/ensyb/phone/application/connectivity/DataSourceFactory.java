package io.github.ensyb.phone.application.connectivity;

import org.apache.commons.dbcp2.BasicDataSource;

import io.github.ensyb.phone.application.configuration.DatabaseConnectionConfiguration;

class DataSourceFactory {

	private final BasicDataSource dataSource;
	private final int defaultInitialPoolSize = 5;
	
	public DataSourceFactory(DatabaseConnectionConfiguration databaseUrl, String username, String password) {
		this.dataSource = new BasicDataSource();
		this.dataSource.setDriverClassName(databaseUrl.driverClassName());
		this.dataSource.setUrl(databaseUrl.databaseConnectionUrl());
		this.dataSource.setUsername(username);
		this.dataSource.setPassword(password);
		this.dataSource.setInitialSize(this.defaultInitialPoolSize);
	}
	
	public BasicDataSource consumeDataSource(){
		return this.dataSource;
	}
}
