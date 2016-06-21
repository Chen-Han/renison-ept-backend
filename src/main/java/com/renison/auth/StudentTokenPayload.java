package com.renison.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class StudentTokenPayload extends BaseTokenPayload {
	private long testSessionId;
	@JsonIgnore
	public static final long DEFAULT_EXP_TIME = 60 * 60 * 5; // default expiry
																// time 5 hour,
																// long enough
																// to handle one
																// test

	public StudentTokenPayload(long testSessionId) {
		super();
		this.testSessionId = testSessionId;
		setExpTime(DEFAULT_EXP_TIME);
	}

	public StudentTokenPayload() {

	}

	public long getTestSessionId() {
		return testSessionId;
	}

	public void setTestSessionId(long testSessionId) {
		this.testSessionId = testSessionId;
	}
}
