package com.print.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class LoginForm {
	
	@NotEmpty(message = "{username.required}")
	private String username;
	
	@NotEmpty(message = "{password.required}")
	private String password;
	
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

}
