package com.calogardev.pizzarella.exception;

import java.io.Serializable;

public class PasswordMismatchException extends Exception implements Serializable {

	private static final long serialVersionUID = -1054628623392325110L;

	private static final String message = "Sorry, the passwords you have entered are not the same.";

	@Override
	public String getMessage() {
		return message;
	}
}
