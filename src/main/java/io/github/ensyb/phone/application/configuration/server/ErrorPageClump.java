package io.github.ensyb.phone.application.configuration.server;

import io.github.ensyb.phone.application.dispatcher.response.Forward;

public class ErrorPageClump {

	private final String errorPageLocation;
	private final int errorNumber;

	public ErrorPageClump(int errorNumber, String errorPageLocation) {
		this.errorNumber = errorNumber;
		this.errorPageLocation = errorPageLocation;
	}

	public String useErrorPageLocation() {
		return "/"+ new Forward(this.errorPageLocation).consumePath();
	}

	public int useErrorNumber() {
		return this.errorNumber;
	}

}
