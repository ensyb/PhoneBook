package io.github.ensyb.phone.application.controller.response;

public class Redirect implements Response {

	private final String resolvedPath;

	public Redirect(final String target) {
		this.resolvedPath = target;
	}

	@Override
	public String consumePath() {
		return this.resolvedPath;
	}

}