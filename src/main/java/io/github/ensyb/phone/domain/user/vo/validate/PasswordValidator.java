package io.github.ensyb.phone.domain.user.vo.validate;

import io.github.ensyb.phone.application.validation.Validator;

public class PasswordValidator implements Validator{

	private final String reason = "password is not valid, must be longer than 5 characters";
	private final String validatorName = "passwordValidator";

	@Override
	public String validatorName() {
		return validatorName;
	}

	@Override
	public <Type> boolean isValid(Type type) {
		String value = (String)type;
		return value.length() > 5;
	}

	@Override
	public String failedReason() {
		return reason;
	}

}
