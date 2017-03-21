package com.calogardev.pizzarella.exception;

public class IngredientWithProductsException extends Exception {

    private static final long serialVersionUID = -4847608375841926999L;

    private static final String message = "An ingredient can't be made of products";

    @Override
    public String getMessage() {
	return message;
    }
}
