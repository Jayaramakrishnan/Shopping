package com.crackers.exceptions;

public class InvalidConfigurationException extends Exception
{

    private static final long serialVersionUID = 1L;
    private String            message          = null;

    public InvalidConfigurationException() {
        super();
    }

    public InvalidConfigurationException(String message) {
        this.message = message;
    }
}
