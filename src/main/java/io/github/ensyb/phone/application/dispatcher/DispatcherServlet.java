package io.github.ensyb.phone.application.dispatcher;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.github.ensyb.phone.application.commands.Command;
import io.github.ensyb.phone.application.commands.NotFoundCommand;
import io.github.ensyb.phone.application.dispatcher.response.Forward;
import io.github.ensyb.phone.application.dispatcher.response.Redirect;
import io.github.ensyb.phone.application.dispatcher.response.Response;
import io.github.ensyb.phone.application.dispatcher.response.Write;

@WebServlet(urlPatterns = {"*.html" }, 
            loadOnStartup = 1, initParams = @WebInitParam(name = "mapingFileName", value = "routs.properties"))
public class DispatcherServlet extends HttpServlet {

	private static final long serialVersionUID = 1995509432339266054L;

	private Map<String, Command> commandMapping = new HashMap<>();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processor(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processor(req, resp);
	}

	@Override
	public void init() throws ServletException {
		String routsFileName = getInitParameter("mapingFileName");

		Properties properties = new Properties();

		try (InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(routsFileName)) {
			if (stream == null) {
				throw new DispatcherException(
						"nemoze pronaci routs file na classpathu");
			}
			properties.load(stream);
			for (Object key : properties.keySet()) {
				String command = (String) key;
				String handlerClassName = properties.getProperty(command);
				try {
					Class<?> handlerClass = Class.forName(handlerClassName);
					Command handlerInstance = (Command) handlerClass.newInstance();
					commandMapping.put(command, handlerInstance);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
					throw new DispatcherException(e);
				}
			}

		} catch (IOException e) {
			throw new DispatcherException(e);
		}
	}

	private void processor(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String commandName = request.getRequestURI().substring(request.getContextPath().length());
		Command command = commandMapping.get(commandName);
		if (command == null) {
			command = new NotFoundCommand();
		}
		try {
			Response viewModel = command.execute(new Request(request, response));
			doAction(viewModel, request, response);
		} catch (Exception e) {
			throw new DispatcherException(e);
		}
	}
	
	private void doAction(Response vm, HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		if(vm instanceof Redirect)
			res.sendRedirect(vm.consumePath());
		if(vm instanceof Forward)
			// dodat flash mesages kasnije :) za proslijedit
			req.getRequestDispatcher(vm.consumePath()).forward(req, res);
		if(vm instanceof Write){
			res.getWriter().println(vm.consumePath());
		}
	}

}
