package com.calogardev.pizzarella.exception;

public class ProductNotFoundException extends Exception {

    private static final long serialVersionUID = -2219650317518550770L;
    private static final String message = "The Product could not be found";

    public ProductNotFoundException() {
    }

    @Override
    public String getMessage() {
	return message;
    }
}
