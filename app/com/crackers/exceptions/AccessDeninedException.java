package com.crackers.exceptions;

public class AccessDeninedException extends Exception {

	private static final long	serialVersionUID	= 1L;
	private String				message				= null;

	public AccessDeninedException() {
		super();
	}

	public AccessDeninedException(String message) {
		this.message = message;
	}
}
