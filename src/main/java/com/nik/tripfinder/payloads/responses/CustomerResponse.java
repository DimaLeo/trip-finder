package com.nik.tripfinder.payloads.responses;

import com.nik.tripfinder.DTO.CustomerDTO.CustomerDTO;

public class CustomerResponse extends GenericResponse{

    private CustomerDTO body;
    private Integer id;

    public CustomerResponse(String status, String message) {
        super(status, message);
    }

    public CustomerResponse(String status, String message, CustomerDTO body, Integer id) {
        super(status, message);
        this.body = body;
        this.id = id;
    }

    public CustomerDTO getBody() {
        return body;
    }

    public Integer getId() {
        return id;
    }

}
