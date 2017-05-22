package com.calogardev.pizzarella.exception;

public class PrivilegeNotFoundException extends Exception {

    private static final long serialVersionUID = -6351150652589716331L;
    private static final String message = "The Privilege could not be found";

    public PrivilegeNotFoundException() {
    }

    @Override
    public String getMessage() {
	return message;
    }
}
