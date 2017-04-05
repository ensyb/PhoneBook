package io.github.ensyb.phone.application.validation;

public interface Validator {
	public boolean isValid();
	public String failedReason();
}
