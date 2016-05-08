package com.renison.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalErrorException extends BaseException {
	public InternalErrorException(Long debugNumber, String errorMessage, String debugMessage) {
		super(debugNumber, errorMessage, debugMessage);
		setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
