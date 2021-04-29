package com.ibm.assessment.domain.common.exception;

import org.springframework.http.HttpStatus;

public abstract class DomainException extends RuntimeException{

    private int status = HttpStatus.BAD_REQUEST.value();

    public DomainException() { super(); }

    public DomainException(String message) { super(message); }

    public DomainException(Throwable cause) { super(cause); }

    public DomainException(String message, Throwable cause) { super(message, cause); }

    public DomainException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public int getStatus() {
        return status;
    }
}
