package io.github.ensyb.phone.application.configuration;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.WebResourceSet;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.EmptyResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.eclipse.jdt.internal.compiler.batch.Main;

public interface WebServer{
	
	 public void startServer(int port);
	 
	 
	 class TomcatConfiguration implements WebServer{

			class TomcatConfigurationException extends RuntimeException{

				private static final long serialVersionUID = 1L;

				public TomcatConfigurationException() {

				}
				
				public TomcatConfigurationException(Throwable cause) {
					super(cause);
				}

			}
			
			@Override
			public void startServer(int port) {
				try {
					File root = getRootFolder();
					System.setProperty("org.apache.catalina.startup.EXIT_ON_INIT_FAILURE", "true");

					Tomcat tomcat = new Tomcat();
					Path tomcatPath;

					tomcatPath = Files.createTempDirectory("tomcat-base-dir");

					tomcat.setBaseDir(tomcatPath.toString());
					tomcat.setPort(port);

					File webContentFolder = new File(root.getAbsolutePath(), "src/main/webapp");
					if (!webContentFolder.exists())
						webContentFolder = Files.createTempDirectory("default-doc-base").toFile();

					StandardContext applicationConatiner = (StandardContext) tomcat.addWebapp("",
							webContentFolder.getAbsolutePath());
					applicationConatiner.setParentClassLoader(getLoader());
					webinfClasses(root, applicationConatiner);
			
					tomcat.start();
					tomcat.getServer().await();

				} catch (Exception e) {
					throw new TomcatConfiguration.TomcatConfigurationException(e);
				}
			}
			
			private ClassLoader getLoader() {
				ClassLoader loader = Thread.currentThread().getContextClassLoader();
				// fallback
				if (loader == null) {
					loader = ClassLoader.class.getClassLoader();
				}
				return loader;
			}
			
			private void webinfClasses(File root, StandardContext applicationConatiner) {
				File additionWebInfClassesFolder = new File(root.getAbsolutePath(), "target/classes");
				WebResourceRoot resources = new StandardRoot(applicationConatiner);

				WebResourceSet resourceSet;
				if (additionWebInfClassesFolder.exists()) {
					resourceSet = new DirResourceSet(resources, "/WEB-INF/classes",
							additionWebInfClassesFolder.getAbsolutePath(), "/");
					//Logger.logInfo
					System.out.println("loading WEB-INF resources from as '" + additionWebInfClassesFolder.getAbsolutePath() + "'");
				} else {
					resourceSet = new EmptyResourceSet(resources);
				}
				resources.addPreResources(resourceSet);
				applicationConatiner.setResources(resources);
			}

			private File getRootFolder() {
				try {
					File root;

					String runningJarPath = Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()
							.replaceAll("\\\\", "/");

					int lastIndexOf = runningJarPath.lastIndexOf("/target/");

					if (lastIndexOf < 0) {
						root = new File("");
					} else {
						root = new File(runningJarPath.substring(0, lastIndexOf));
					}
					//Logger.logInfo
					System.out.println("application resolved root folder: " + root.getAbsolutePath());

					return root;
				} catch (URISyntaxException ex) {
					throw new RuntimeException(ex);
				}
			}
		}

	 
}

