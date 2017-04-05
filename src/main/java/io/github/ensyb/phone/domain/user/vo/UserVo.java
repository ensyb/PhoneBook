package io.github.ensyb.phone.domain.user.vo;

public class UserVo {

	private int id;
	private final String email;
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
	
	public UserVo updateId(final int id){
		return new UserVo(id, this.email, this.password);
	}
	
	public UserVo updateEmail(final String email){
		return new UserVo(this.id, email, this.password);
	}
	
	public UserVo updatePassword(final String password){
		return new UserVo(this.id, this.email, password);
	}
}
