package io.github.ensyb.phone.application.dispatcher.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbcp2.BasicDataSource;

import io.github.ensyb.phone.application.repository.CommonJdbcRepository;
import io.github.ensyb.phone.application.repository.CommonJdbcRepository.Repository;

public interface Request {

	public boolean isRequestAjax();

	public BasicDataSource useDataSource();
	
	public CommonJdbcRepository useCommonJdbcRepository();

	public void setAttribute(final String name, final Object object);

	public void setAttributeInSession(final String name, final Object object);

	public Object getAttribute(final String name);

	public Object getAttributeFromSession(final String name);
	
	public String getParameter(final String name);

	public void closeSession();

	public static class PhonebookRequest implements Request {

		private final String xmlHttpR = "XMLHttpRequest";

		private final HttpServletRequest request;

		public PhonebookRequest(HttpServletRequest request, HttpServletResponse response) {
			this.request = request;
		}
		
		@Override
		public boolean isRequestAjax() {
			return xmlHttpR.equals(request.getHeader("X-Requested-With"));
		}

		@Override
		public BasicDataSource useDataSource() {
			return (BasicDataSource) this.request.getServletContext().getAttribute("dataSource");
		}

		@Override
		public CommonJdbcRepository useCommonJdbcRepository() {
			return (Repository)this.request.getServletContext().getAttribute("repository");
		}
		
		@Override
		public void setAttribute(final String name, final Object object) {
			this.request.setAttribute(name, object);
		}

		@Override
		public void setAttributeInSession(final String name, final Object object) {
			this.request.getSession().setAttribute(name, object);
		}

		@Override
		public Object getAttribute(final String name) {
			return this.request.getAttribute(name);
		}

		@Override
		public Object getAttributeFromSession(final String name) {
			return this.request.getSession().getAttribute(name);
		}

		@Override
		public String getParameter(final String name) {
			return this.request.getParameter(name);
		}

		@Override
		public void closeSession() {
			this.request.getSession().invalidate();
			this.request.getSession(false);
		}

	
	}

}
