package com.project.dairyproject.UserDefinedExceptions;

public class ProductNotFoundException extends RuntimeException {

	public ProductNotFoundException(String message) {
		super(message);
	}

}
