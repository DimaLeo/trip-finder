package com.nik.tripfinder.payloads.responses;

public abstract class GenericErrorResponse {
    private String status;
    private String message;

    public GenericErrorResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
