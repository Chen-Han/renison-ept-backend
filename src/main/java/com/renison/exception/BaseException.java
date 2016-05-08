package com.renison.exception;

import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException {
	private Long debugNumber;
	private String debugMessage; // should include details of the exception
	private Long errorNumber;
	private HttpStatus httpCode;
	private Object data;

	public BaseException(Long debugNumber, String errorMessage, String debugMessage) {
		super(errorMessage);
		this.debugMessage = debugMessage;
		this.debugNumber = debugNumber;
	}

	public BaseException(Long debugNumber, String errorMessage, String debugMessage, Long errorNumber) {
		this(debugNumber, errorMessage, debugMessage);
		this.errorNumber = errorNumber;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Long getDebugNumber() {
		return debugNumber;
	}

	public void setDebugNumber(Long debugNumber) {
		this.debugNumber = debugNumber;
	}

	public String getDebugMessage() {
		return debugMessage;
	}

	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}

	public Long getErrorNumber() {
		return errorNumber;
	}

	public void setErrorNumber(Long errorNumber) {
		this.errorNumber = errorNumber;
	}

	public HttpStatus getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(HttpStatus httpCode) {
		this.httpCode = httpCode;
	}

}
