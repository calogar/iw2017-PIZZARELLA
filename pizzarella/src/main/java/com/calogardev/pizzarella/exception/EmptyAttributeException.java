package com.calogardev.pizzarella.exception;

public class EmptyAttributeException extends Exception {

	private static final long serialVersionUID = -1619675930900438361L;

	private static final String message = "Entity could not be saved: some fields are empty";

	@Override
	public String getMessage() {
		return message;
	}
}
