package com.renison.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class LoginTokenException extends BaseException {
	public static LoginTokenException instance = new LoginTokenException(202356133744l, "default login token exception",
			"invalid login token");

	public LoginTokenException(Long debugNumber, String errorMessage, String debugMessage) {
		super(debugNumber, errorMessage, debugMessage);
		setHttpCode(HttpStatus.BAD_REQUEST);

	}

}
