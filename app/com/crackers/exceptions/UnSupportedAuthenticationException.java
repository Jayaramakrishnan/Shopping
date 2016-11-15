package com.crackers.exceptions;

public class UnSupportedAuthenticationException extends Exception
{

    private static final long serialVersionUID = 1L;
    private String            message          = null;

    public UnSupportedAuthenticationException() {
        super();
    }

    public UnSupportedAuthenticationException(String message) {
        this.message = message;
    }
}
