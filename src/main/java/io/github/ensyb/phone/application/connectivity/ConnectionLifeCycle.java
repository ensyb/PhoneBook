package io.github.ensyb.phone.application.connectivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import io.github.ensyb.phone.application.configuration.DatabaseConnectionConfiguration;

@WebListener
public class ConnectionLifeCycle implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		Properties properties = new Properties();

		try (InputStream stream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("databaseConnectionProperties.properties")) {
			if (stream == null) {
				throw new ConnectivityException(
						"nemoze pronaci databaseConnectionProperties.properties file na classpathu");
			}

			properties.load(stream);
			// Logger.logInfo
			System.out.println("Connecting to database...");
			ServletContext context = event.getServletContext();
			DataSourceFactory dataF = new DataSourceFactory(
					new DatabaseConnectionConfiguration.MysqlConfiguration(properties.getProperty("dbHost"),
							properties.getProperty("dbName"), Integer.parseInt(properties.getProperty("port"))),
					properties.getProperty("username"), properties.getProperty("password"));
			context.setAttribute("dataSource", dataF.consumeDataSource(4));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// Logger.logInfo
		System.out.println("application is shutting down...");

	}

}
