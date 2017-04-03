package io.github.ensyb.phone.application.controller;

public class ControllerException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String message;

	public ControllerException() {

	}
	
	public ControllerException(Throwable cause) {
		super(cause);
	}

	public ControllerException(String message) {
		this.message = message;

	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
