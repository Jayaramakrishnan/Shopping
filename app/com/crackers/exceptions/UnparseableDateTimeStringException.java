package com.crackers.exceptions;

public class UnparseableDateTimeStringException extends Exception
{

	private static final long	serialVersionUID	= 1L;
	private String				message				= null;

	public UnparseableDateTimeStringException() {
		super();
	}

	public UnparseableDateTimeStringException(String message) {
		this.message = message;
	}
}
