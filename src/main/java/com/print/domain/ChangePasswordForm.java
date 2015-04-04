package com.print.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class ChangePasswordForm {
	
	@NotEmpty(message = "{username.required}")
	private String usernamepwdid;
	
	@NotEmpty(message = "{currentpassword.required}")
	private String currentpassword;
	
	@NotEmpty(message = "{newpassword.required}")
	private String newpassword;
	
	@NotEmpty(message = "{confirmpassword.required}")
	private String confirmpassword;

	public String getUsernamepwdid() {
		return usernamepwdid;
	}

	public void setUsernamepwdid(String usernamepwdid) {
		this.usernamepwdid = usernamepwdid;
	}

	public String getCurrentpassword() {
		return currentpassword;
	}

	public void setCurrentpassword(String currentpassword) {
		this.currentpassword = currentpassword;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

}
