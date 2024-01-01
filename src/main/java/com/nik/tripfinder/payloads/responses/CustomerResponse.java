package com.nik.tripfinder.payloads.responses;

import com.nik.tripfinder.DTO.CustomerDTO.CustomerDTO;

public class CustomerResponse extends GenericResponse{

    private CustomerDTO body;

    public CustomerResponse(String status, String message) {
        super(status, message);
    }

    public CustomerResponse(String status, String message, CustomerDTO body) {
        super(status, message);
        this.body = body;
    }
}
