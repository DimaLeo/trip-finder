package com.nik.tripfinder.payloads.responses;

import com.nik.tripfinder.DTO.UserDTO.UserDTO;

public class AuthenticationResponse extends GenericResponse{

    private UserDTO body;

    public AuthenticationResponse(String status, String message) {
        super(status, message);
    }

    public AuthenticationResponse(String status, String message, UserDTO body) {
        super(status, message);
        this.body = body;
    }

    public UserDTO getBody() {
        return body;
    }
}
