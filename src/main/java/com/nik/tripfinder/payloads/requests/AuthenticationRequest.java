package com.nik.tripfinder.payloads.requests;

public class AuthenticationRequest {

    private String username;
    private String password;

    public AuthenticationRequest(){

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
