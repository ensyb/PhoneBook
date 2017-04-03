package io.github.ensyb.phone.application.connectivity;

public class ConnectivityException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private String message;

	public ConnectivityException() {

	}
	
	public ConnectivityException(Throwable cause) {
		super(cause);
	}

	public ConnectivityException(String message) {
		this.message = message;

	}

	@Override
	public String getMessage() {
		return this.message;
	}


}
