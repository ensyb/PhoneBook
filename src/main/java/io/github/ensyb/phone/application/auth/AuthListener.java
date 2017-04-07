package io.github.ensyb.phone.application.auth;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class AuthListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		Properties properties = new Properties();

		Map<String, String> authMap = new HashMap<>();
		try (InputStream stream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("authorization.properties")) {
			if (stream == null) {
				throw new AuthException(
						"nemoze pronaci authorization.properties file na classpathu");
			}
			//Logger.logInfo()
			System.out.println("Loading authorized mappings ");
			properties.load(stream);
			for (Object obj : properties.keySet()) {
				String key = (String)obj;
				String value = properties.getProperty(key);
				authMap.put(key, value);
			}
			sce.getServletContext().setAttribute("authMap",authMap);
		} catch (IOException e) {
			throw new AuthException(e);
		}
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
