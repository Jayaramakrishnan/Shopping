package com.crackers.exceptions;

import org.apache.commons.lang.exception.ExceptionUtils;

public class FormFieldException extends Exception
{

    private static final long serialVersionUID = 1L;

    public FormFieldException(String msg) {
        super(msg);
    }

    public FormFieldException(String msg, Throwable cause) {
        super(msg);
        ExceptionUtils.setCause(this, cause);
    }
}