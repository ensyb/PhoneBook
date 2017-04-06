package io.github.ensyb.phone.domain.user.vo.validate;

import java.util.regex.Pattern;

import io.github.ensyb.phone.application.validation.Validator;

public class EmailValidator implements Validator {

	private static final String failed = "email is not valid";
	private final String validatorName = "emailValidator";
	
	private Pattern pattern = Pattern
			.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

	@Override
	public String validatorName() {
		return validatorName;
	}

	@Override
	public <Type> boolean isValid(Type type) {
		String value = (String) type;
		return pattern.matcher(value).matches();
	}

	@Override
	public String failedReason() {
		return failed;
	}

}
