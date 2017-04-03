package io.github.ensyb.phone.application;

import io.github.ensyb.phone.application.configuration.WebServer;

public class ApplicationMain {

	public static void main(String... args) {
		System.out.println("started");
		WebServer server = new WebServer.TomcatConfiguration();
		server.startServer(8080);
	}

}
