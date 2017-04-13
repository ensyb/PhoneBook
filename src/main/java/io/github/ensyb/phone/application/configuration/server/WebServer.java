package io.github.ensyb.phone.application.configuration.server;

import java.io.File;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.tomcat.util.descriptor.web.ErrorPage;

public interface WebServer {

	public void startServer(int port);

	public static class TomcatConfiguration implements WebServer {

		private class TomcatConfigurationException extends RuntimeException {

			private static final long serialVersionUID = 1L;

			public TomcatConfigurationException(Throwable cause) {
				super(cause);
			}
		}

		private StandardContext context;
		private final ErrorPageClump[] errorPages;

		public TomcatConfiguration(ErrorPageClump... errorPages) {
			this.errorPages = errorPages;
		}

		@Override
		public void startServer(int port) {
			try {
				Tomcat tomcat = new Tomcat();
				tomcat.setPort(port);

				this.context = (StandardContext) tomcat.addWebapp("/", new File("src/main/webapp").getAbsolutePath());
				// log
				System.out.println("tomcat is starting...");

				File classesFromWebInf = new File("target/classes");
				WebResourceRoot resources = new StandardRoot(this.context);
				resources.addPreResources(
						new DirResourceSet(resources, "/WEB-INF/classes", classesFromWebInf.getAbsolutePath(), "/"));
				this.context.setResources(resources);

				SetupErrorPages();
				
				tomcat.start();
				tomcat.getServer().await();
			} catch (Exception e) {
				throw new TomcatConfiguration.TomcatConfigurationException(e);
			}
		}

		private void SetupErrorPages() {
			for (ErrorPageClump errorPage : errorPages) {
				ErrorPage error = new ErrorPage();
				error.setErrorCode(errorPage.useErrorNumber());
				error.setLocation(errorPage.useErrorPageLocation());
				this.context.addErrorPage(error);
				
			}
		}

	}

}
