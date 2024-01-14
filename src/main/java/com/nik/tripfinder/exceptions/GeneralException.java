package com.nik.tripfinder.exceptions;

import org.springframework.http.HttpStatus;

public class GeneralException extends Exception {
    private HttpStatus status;

    public GeneralException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public GeneralException(String message) {
        super(message);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
