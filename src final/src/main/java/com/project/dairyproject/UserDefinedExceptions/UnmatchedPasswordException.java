package com.project.dairyproject.UserDefinedExceptions;

public class UnmatchedPasswordException extends RuntimeException {
	public UnmatchedPasswordException(String message) {
		super(message);
	}
}
