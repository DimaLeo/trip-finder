package com.nik.tripfinder.payloads.responses;

import java.util.List;

import com.nik.tripfinder.DTO.AgencyDTO.AllAgenciesDTO;

public class AllAgenciesResponse extends GenericResponse{

    private List<AllAgenciesDTO> body;
    public AllAgenciesResponse(String status, String message, List<AllAgenciesDTO> list) {
        super(status, message);
        this.body = list;
    }
    

    public AllAgenciesResponse(String status, String message) {
        super(status, message);
    }

    public List<AllAgenciesDTO> getBody() {
        return body;
    }
}
