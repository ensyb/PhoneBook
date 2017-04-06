package io.github.ensyb.phone.application.dispatcher;

public class DispatcherException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String message;

	public DispatcherException() {

	}
	
	public DispatcherException(Throwable cause) {
		super(cause);
	}

	public DispatcherException(String message) {
		this.message = message;

	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
