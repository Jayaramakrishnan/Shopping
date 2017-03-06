package com.crackers.exceptions;

public class UnauthorizedException extends Exception {

	private static final long	serialVersionUID	= 1L;
	private String				message				= null;

	public UnauthorizedException() {
		super();
	}

	public UnauthorizedException(String message) {
		this.message = message;
	}
}