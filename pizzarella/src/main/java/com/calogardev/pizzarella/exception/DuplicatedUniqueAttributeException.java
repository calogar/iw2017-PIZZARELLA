package com.calogardev.pizzarella.exception;

public class DuplicatedUniqueAttributeException extends Exception {

	private static final long serialVersionUID = 2991523418923178195L;

	private String attributeName;

	public DuplicatedUniqueAttributeException(String attributeName) {
		this.attributeName = attributeName;
	}

	@Override
	public String getMessage() {
		return "A record with this " + attributeName + " already exists.";
	}
}
