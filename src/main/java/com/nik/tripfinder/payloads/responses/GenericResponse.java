package com.nik.tripfinder.payloads.responses;

import org.springframework.http.HttpStatus;

public abstract class GenericResponse {
    private String status;
    private String message;
    private HttpStatus statusCode;

    public GenericResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public GenericResponse(String status, String message, HttpStatus statusCode) {
        this.status = status;
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
