package com.project.dairyproject.UserDefinedExceptions;

public class UsernameFoundException extends RuntimeException {

	public UsernameFoundException(String message) {
		super(message);
	}

}
