package com.project.dairyproject.UserDefinedExceptions;

public class SellerNotFoundException extends RuntimeException {

	public SellerNotFoundException(String message) {
		super(message);
	}

}
