package io.github.ensyb.phone.application.repository;

public class CommonRepositoryException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CommonRepositoryException() {
		super();
	}
	
	public CommonRepositoryException(String message) {
		super(message);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}