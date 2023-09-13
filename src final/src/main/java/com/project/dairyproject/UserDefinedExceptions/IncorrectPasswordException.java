package com.project.dairyproject.UserDefinedExceptions;

public class IncorrectPasswordException extends RuntimeException {
	public IncorrectPasswordException(String message) {
		super(message);
	}
}
