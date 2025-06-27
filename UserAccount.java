package com.nit.mv.pojo;

public class UserAccount {
	
	private long 	userId;			
	private String 	username;	
	private String 	password;	
	private String 	email;		
	private long 	mobile;		
	
	public UserAccount() {
		//no-op
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "UserAccountsPojo(userId=" + userId + ", username=" + username + ", password=" + password + ", email="
				+ email + ", mobile=" + mobile + ")";
	}
	
}