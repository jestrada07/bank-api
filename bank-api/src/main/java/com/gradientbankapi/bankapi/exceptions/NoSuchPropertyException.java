package com.gradientbankapi.bankapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchPropertyException extends RuntimeException {
    @Serial
    private static final long serialVersionUUID = 1L;


    public NoSuchPropertyException() {
    }

    public NoSuchPropertyException(String message) {
        super(message);
    }

    public NoSuchPropertyException(String message, Throwable cause) {
        super(message, cause);
    }



}
