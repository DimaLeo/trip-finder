package com.nik.tripfinder.payloads.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.aspectj.lang.annotation.RequiredTypes;

public class NewAgencyRequest {
    private String username;
    private String password;
    @JsonProperty("user_type")
    private String userType;
    @JsonProperty("tax_code")
    private String taxCode;
    @JsonProperty("brand_name")
    private String brandName;
    private String owner;

    public NewAgencyRequest() {

    }

    public NewAgencyRequest(String username, String password, String userType, String taxCode, String brandName, String owner) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.taxCode = taxCode;
        this.brandName = brandName;
        this.owner = owner;
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

    public String getBrandName() {
        return brandName;
    }

    public String getOwner() {
        return owner;
    }
}
