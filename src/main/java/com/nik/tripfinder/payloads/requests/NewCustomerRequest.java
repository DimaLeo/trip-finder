package com.nik.tripfinder.payloads.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nik.tripfinder.models.User;
import jakarta.persistence.*;

public class NewCustomerRequest {

    private String username;
    private String password;
    @JsonProperty("user_type")
    private String userType;
    @JsonProperty("tax_code")
    private String taxCode;
    private String name;
    private String surname;
    private String email;

    public NewCustomerRequest(){

    }

    public NewCustomerRequest(String username, String password, String userType, String taxCode, String name, String surname, String email) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.taxCode = taxCode;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUserType() {
        return userType;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }
}
