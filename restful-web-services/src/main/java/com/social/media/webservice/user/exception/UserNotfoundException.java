package com.social.media.webservice.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class UserNotfoundException extends RuntimeException {

	public UserNotfoundException(String message) {
		super(message);
	}
}
