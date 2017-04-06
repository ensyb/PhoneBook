package io.github.ensyb.phone.application.validation;

public interface Validator {
	public String validatorName();
	public <Type> boolean isValid(Type type);
	public String failedReason();
}
