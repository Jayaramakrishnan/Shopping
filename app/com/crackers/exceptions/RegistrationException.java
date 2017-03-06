package com.crackers.exceptions;

public class RegistrationException extends Exception {

	private static final long	serialVersionUID	= 1L;
	private String				message				= null;

	public RegistrationException() {
		super();
	}

	public RegistrationException(String message) {
		this.message = message;
	}
}
