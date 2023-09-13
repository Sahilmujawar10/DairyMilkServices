package com.project.dairyproject.UserDefinedExceptions;

public class ConsumerNotFoundException extends RuntimeException {

	public ConsumerNotFoundException(String messgae) {
		super(messgae);
	}

}
