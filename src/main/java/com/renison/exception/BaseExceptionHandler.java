package com.renison.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(annotations = { RestController.class })
public class BaseExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler({ BaseException.class })
	protected ResponseEntity<Object> handleBaseException(RuntimeException e, WebRequest request) {
		BaseException be = (BaseException) e;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		Map<String, Object> errorResponse = new HashMap<>();
		errorResponse.put("errorNumber", be.getErrorNumber());
		errorResponse.put("debugNumber", be.getDebugNumber());
		errorResponse.put("errorMessage", be.getLocalizedMessage());
		errorResponse.put("debugMessage", be.getDebugMessage());
		return handleExceptionInternal(be, errorResponse, headers, be.getHttpCode(), request);
	}
}
