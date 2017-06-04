package com.calogardev.pizzarella.exception;

public class ProductFamilyNotFoundException extends Exception {

    private static final long serialVersionUID = 8055364204200040856L;
    private static final String message = "The product family could not be found";

    public ProductFamilyNotFoundException() {
    }

    @Override
    public String getMessage() {
	return message;
    }
}
