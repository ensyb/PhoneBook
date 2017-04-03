package io.github.ensyb.phone.application.configuration;

public interface DatabaseConnectionConfiguration{
	public String databaseConnectionUrl();
	public String driverClassName();
	
	 class MysqlConfiguration implements DatabaseConnectionConfiguration {

			private final String mysqlDriverClass = "com.mysql.jdbc.Driver";
			private final String databaseHostName;
			private final String databaseName;
			private final int port;
			
			public MysqlConfiguration(String databaseHostName,String databaseName, int port) {
				this.databaseHostName = databaseHostName;
				this.databaseName = databaseName;
				this.port = port;
			}
			
			public String databaseConnectionUrl(){
				return new StringBuilder("jdbc:mysql://")
						.append(this.databaseHostName)
						.append(":").append(this.port)
						.append("/").append(databaseName).toString();
			}

			@Override
			public String driverClassName() {
				return mysqlDriverClass;
			}
		}
}

