package com.crackers.exceptions;

public class InternalServerException extends Exception
{

    private static final long serialVersionUID = 1L;
    private String            message;

    public InternalServerException() {
        super();
    }

    public InternalServerException(String message) {
        this.message = message;
    }
}
