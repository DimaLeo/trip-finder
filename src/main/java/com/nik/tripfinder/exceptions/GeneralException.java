package com.nik.tripfinder.exceptions;

import org.springframework.http.HttpStatus;

public class GeneralException extends Exception{
    private HttpStatus status;

    public GeneralException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
