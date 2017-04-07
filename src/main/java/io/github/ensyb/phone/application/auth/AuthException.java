package io.github.ensyb.phone.application.auth;

public class AuthException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;

	public AuthException() {

	}
	
	public AuthException(Throwable cause) {
		super(cause);
	}

	public AuthException(String message) {
		this.message = message;

	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
