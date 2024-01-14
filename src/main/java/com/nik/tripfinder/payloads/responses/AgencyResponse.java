package com.nik.tripfinder.payloads.responses;

import com.nik.tripfinder.DTO.AgencyDTO.AgencyDTO;

public class AgencyResponse extends GenericResponse{

    private AgencyDTO body;
    private Integer id;

    public AgencyResponse(String status, String message, AgencyDTO body, Integer id) {
        super(status, message);
        this.body = body;
        this.id = id;
    }

    public AgencyResponse(String status, String message) {
        super(status, message);
    }

    public AgencyDTO getBody() {
        return body;
    }

    public Integer getId() {
        return id;
    }
}
