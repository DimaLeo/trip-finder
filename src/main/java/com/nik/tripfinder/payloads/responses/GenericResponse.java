package com.nik.tripfinder.payloads.responses;

public abstract class GenericResponse {
    private String status;
    private String message;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public GenericResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
