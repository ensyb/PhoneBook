package io.github.ensyb.phone.application.connectivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.commons.dbcp2.BasicDataSource;

import io.github.ensyb.phone.application.configuration.database.DatabaseConnectionConfiguration;
import io.github.ensyb.phone.application.repository.CommonJdbcRepository;

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
			//ovo 4 nebi trebalo bit hardcoded
			BasicDataSource dataSource = dataF.consumeDataSource(4);
			context.setAttribute("dataSource", dataSource);
			
			CommonJdbcRepository repository = new CommonJdbcRepository.Repository(dataSource);
			context.setAttribute("repository", repository);
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
