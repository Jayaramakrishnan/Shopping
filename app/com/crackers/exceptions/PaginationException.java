package com.crackers.exceptions;


public class PaginationException extends Exception
{

	private static final long	serialVersionUID	= 1L;
	private String				message				= null;

	public PaginationException() {
		super();
	}

	public PaginationException(String message) {
		this.message = message;
	}
}