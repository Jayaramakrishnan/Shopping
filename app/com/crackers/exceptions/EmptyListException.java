package com.crackers.exceptions;

import org.apache.commons.lang.exception.ExceptionUtils;

public class EmptyListException extends Exception
{

    private static final long serialVersionUID = 1L;

    public EmptyListException(String msg) {
        super(msg);
    }

    public EmptyListException(String msg, Throwable cause) {
        super(msg);
        ExceptionUtils.setCause(this, cause);
    }
}