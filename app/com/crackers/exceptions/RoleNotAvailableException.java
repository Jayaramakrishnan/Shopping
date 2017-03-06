package com.crackers.exceptions;

import org.apache.commons.lang.exception.ExceptionUtils;

public class RoleNotAvailableException extends Exception {

	private static final long serialVersionUID = 1L;

	public RoleNotAvailableException(String msg) {
		super(msg);
	}

	public RoleNotAvailableException(String msg, Throwable cause) {
		super(msg);
		ExceptionUtils.setCause(this, cause);
	}
}