package com.calogardev.pizzarella.exception;

public class RoleNotFoundException extends Exception {

	private static final long serialVersionUID = -6351150652589716331L;
	private static final String message = "The Role could not be found";

	public RoleNotFoundException() {
	}

	@Override
	public String getMessage() {
		return message;
	}
}
