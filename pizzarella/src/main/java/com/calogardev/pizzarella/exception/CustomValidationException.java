package com.calogardev.pizzarella.exception;

public class CustomValidationException extends Exception {
    private static final long serialVersionUID = -4822817465649754480L;

    private static String message;

    public CustomValidationException(String message) {
	this.message = message;
    }

    @Override
    public String getMessage() {
	return message;
    }

}
