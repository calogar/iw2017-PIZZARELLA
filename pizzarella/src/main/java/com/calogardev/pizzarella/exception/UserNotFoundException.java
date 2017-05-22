package com.calogardev.pizzarella.exception;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = -2219650317518550770L;
	private static final String message = "The User could not be found";

	public UserNotFoundException() {
	}

	@Override
	public String getMessage() {
		return message;
	}
}
