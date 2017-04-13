package io.github.ensyb.phone.application;

import javax.servlet.http.HttpServletResponse;

import io.github.ensyb.phone.application.configuration.server.ErrorPageClump;
import io.github.ensyb.phone.application.configuration.server.WebServer;

public class ApplicationMain {

	public static void main(String... args) {
		ApplicationMain application = new ApplicationMain();
		application.startServer();
	}
	
	public void startServer(){
		String envVar = System.getenv("PORT");
		Integer port = 8080;
		if(envVar != null && !envVar.trim().isEmpty())
			port = Integer.parseInt(envVar);
		
		WebServer server = new WebServer.TomcatConfiguration(
				new ErrorPageClump(HttpServletResponse.SC_NOT_FOUND, "notFound"));
		
		server.startServer(port);
		
	}

}
