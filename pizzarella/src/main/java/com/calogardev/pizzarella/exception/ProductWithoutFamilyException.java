package com.calogardev.pizzarella.exception;

public class ProductWithoutFamilyException extends Exception {

    private static final long serialVersionUID = 8035676989802270327L;

    private static final String message = "A product must have an associated family";

    @Override
    public String getMessage() {
	return message;
    }
}
