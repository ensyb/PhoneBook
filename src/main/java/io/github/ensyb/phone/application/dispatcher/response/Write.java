package io.github.ensyb.phone.application.dispatcher.response;

public class Write implements Response {

	private String response;

	public Write(final String response) {
		this.response = response;
	
	}
	
	@Override
	public String consumePath() {
		return this.response;
	}

}
