package com.renison.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends BaseException {
	public NotFoundException(Long debugNumber, String errorMessage, String debugMessage) {
		super(debugNumber, errorMessage, debugMessage);
		setHttpCode(HttpStatus.NOT_FOUND);
	}
}
