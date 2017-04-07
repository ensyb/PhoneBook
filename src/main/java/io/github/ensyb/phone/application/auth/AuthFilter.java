package io.github.ensyb.phone.application.auth;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthFilter implements Filter {

	private Map<String, String> authMap = new HashMap<>();

	@Override
	@SuppressWarnings("unchecked")
	public void init(FilterConfig filterConfig) throws ServletException {
		this.authMap = (Map<String, String>) filterConfig.getServletContext().getAttribute("authMap");

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession(false);
		String url = request.getRequestURI().substring(request.getContextPath().length());
		String atrName = authMap.get(url);
		
		if (atrName != null && !atrName.isEmpty()) {
			if (session != null && session.getAttribute(atrName) != null)
				chain.doFilter(request, response);
			else
				request.getRequestDispatcher("WEB-INF/view/index.jsp").forward(request, response);
		}else{
				chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {

	}

}
