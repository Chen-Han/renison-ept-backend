package com.renison.auth;

public class AdminTokenPayload extends BaseTokenPayload {
	public String role = "ADMIN";

	public AdminTokenPayload() {
		setExpTime(30 * 60); // expire in 30min
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
