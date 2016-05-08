package com.renison.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ProctorException extends BaseException {
	public ProctorException(Long debugNumber, String errorMessage, String debugMessage) {
		super(debugNumber, errorMessage, debugMessage);
		setHttpCode(HttpStatus.BAD_REQUEST);
	}

	public ProctorException(Long debugNumber, String errorMessage, String debugMessage, Long errorNumber) {
		this(debugNumber, errorMessage, debugMessage);
		setErrorNumber(errorNumber);
	}
}
