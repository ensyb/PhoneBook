package io.github.ensyb.phone.application.configuration.database;

public class H2Configuration implements DatabaseConnectionConfiguration{

	private final String databaseUrl = "jdbc:h2:mem:phonebook"
			+";mvcc=true;lock_timeout=10000;lock_mode=0;AUTOCOMMIT=FALSE;DATABASE_TO_UPPER=false";
	
	private final String driverClassName = "org.h2.Driver";
	
	@Override
	public String databaseConnectionUrl() {
		return this.databaseUrl;
	}

	@Override
	public String driverClassName() {
		return this.driverClassName;
	}

}
