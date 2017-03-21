package com.calogardev.pizzarella.exception;

public class ShortAttributeException extends Exception {

	private static final long serialVersionUID = 5203213534110682962L;
	private String attribute;
	private Integer minSize;

	public ShortAttributeException(String attribute, Integer minSize) {
		this.attribute = attribute;
		this.minSize = minSize;
	}

	@Override
	public String getMessage() {
		return "The attribute " + attribute + " must be at least " + minSize + " characters long.";
	}

}
