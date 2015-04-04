package com.print.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class RegisterForm {
	
	@NotEmpty(message = "{username.required}")
	private String usernameid;
	
	@NotEmpty(message = "{password.required}")
	private String passwordid;
	
	@NotEmpty(message = "{email.required}")
	private String email;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@NotEmpty(message = "{firstname.required}")
	private String firstname;
	
	@NotEmpty(message = "{lastname.required}")
	private String lastname;
	
	public String getUsernameid() {
		return usernameid;
	}
	public void setUsernameid(String usernameid) {
		this.usernameid = usernameid;
	}
	public String getPasswordid() {
		return passwordid;
	}
	public void setPasswordid(String passwordid) {
		this.passwordid = passwordid;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
}
