package com.project.dairyproject.UserDefinedExceptions;

public class EmailAddressFoundException extends RuntimeException {

	public EmailAddressFoundException(String message) {
		super(message);
	}

}
