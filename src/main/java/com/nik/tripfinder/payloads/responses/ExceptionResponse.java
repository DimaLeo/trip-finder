package com.nik.tripfinder.payloads.responses;

import org.springframework.http.HttpStatus;

public class ExceptionResponse extends GenericErrorResponse{
    public ExceptionResponse(String status, String message) {
        super(status, message);
    }
}
