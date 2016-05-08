package com.renison.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class AuthException extends BaseException {
	public AuthException(Long debugNumber, String errorMessage, String debugMessage) {
		super(debugNumber, errorMessage, debugMessage);
		setHttpCode(HttpStatus.UNAUTHORIZED);
	}
}
