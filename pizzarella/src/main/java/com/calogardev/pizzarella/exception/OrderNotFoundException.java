package com.calogardev.pizzarella.exception;

public class OrderNotFoundException extends Exception {

    private static final long serialVersionUID = -2219650317518550770L;
    private static final String message = "The Order could not be found";

    public OrderNotFoundException() {
    }

    @Override
    public String getMessage() {
	return message;
    }
}
