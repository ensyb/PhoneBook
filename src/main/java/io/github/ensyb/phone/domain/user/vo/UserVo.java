package io.github.ensyb.phone.domain.user.vo;

import org.mindrot.jbcrypt.BCrypt;

import io.github.ensyb.phone.application.validation.Validate;
import io.github.ensyb.phone.domain.user.vo.validate.EmailValidator;
import io.github.ensyb.phone.domain.user.vo.validate.PasswordValidator;

public class UserVo {

	private int id;
	@Validate(name="email", validators={EmailValidator.class})
	private final String email;
	@Validate(name="password", validators={PasswordValidator.class})
	private final String password;
	
	public UserVo(int id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}
	
	public UserVo(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public int userId(){
		return this.id;
	}
	
	public String userEmail(){
		return this.email;
	}
	
	public String userPassword(){
		return this.password;
	}
	
	public UserVo createPassword(final String password){
		return new UserVo(this.id, this.email, BCrypt.hashpw(password, BCrypt.gensalt(12)));
	}
}
