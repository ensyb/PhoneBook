package io.github.ensyb.phone.application.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.github.ensyb.phone.domain.user.vo.validate.EmailValidator;
import io.github.ensyb.phone.domain.user.vo.validate.PasswordValidator;

public class UserVoValidationsTest {
	
	@Test
	public void testValiPassword() {
		ValidationChecker check = new ValidationChecker(new TestVo("ovaj.dobar@gmail.com","password"));
		assertFalse(check.generateReport().contains("passwordValidator"));
	}
	
	@Test
	public void testNotValidPassword() {
		ValidationChecker check = new ValidationChecker(new TestVo("nije.dobar@.com","pass"));
		assertTrue(check.generateReport().contains("passwordValidator"));
	}
	
	@Test
	public void testValidEmail() {
		ValidationChecker check = new ValidationChecker(new TestVo("ovaj.dobar@gmail.com","password"));
		assertFalse(check.generateReport().contains("emailValidator"));
	}
	
	@Test
	public void testNotValidEmail() {
		ValidationChecker check = new ValidationChecker(new TestVo("nije.dobar@.com","password"));
		assertTrue(check.generateReport().contains("emailValidator"));
	}
	
	public static class TestVo{
		@Validate(name="email", validators={EmailValidator.class})
		private final String email;
		
		@Validate(name="password", validators={PasswordValidator.class}) 
		private final String password;
		
		public TestVo(final String email, final String password) {
			this.email = email;
			this.password = password;
		}
	}
}
