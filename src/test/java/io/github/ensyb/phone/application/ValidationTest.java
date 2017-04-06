package io.github.ensyb.phone.application;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.github.ensyb.phone.application.validation.Validate;
import io.github.ensyb.phone.application.validation.ValidationChecker;
import io.github.ensyb.phone.application.validation.Validator;

public class ValidationTest {

	@Test
	public void testValidationReportOneValidatorOnOneField() {
		TestVo vo = new TestVo("123");
		ValidationChecker checker = new ValidationChecker(vo);
		String reportMustBe = "field TestValidator testFailed";
		
		assertEquals(reportMustBe,checker.generateReport());
	}
	
	@Test
	public void testValidationReportEmptyNoErrors() {
		TestVo vo = new TestVo("123456");
		ValidationChecker checker = new ValidationChecker(vo);
		String reportMustBe = "";
		
		assertEquals(reportMustBe,checker.generateReport());
	}
	
	
	class TestVo{
		
		@Validate(name="field", validators = { myValidatorForTesting.class })
		private String field;
		
		public TestVo(String value) {
			this.field = value;
		}
	}
	
	public static class myValidatorForTesting implements Validator{
		@Override
		public String validatorName() {
			return "TestValidator";
		}

		@Override
		public <Type> boolean isValid(Type type) {
			String value = (String)type;
			return value.length() > 5 ;
		}
		
		@Override
		public String failedReason() {
			return "testFailed";
		}
		
	}
}
