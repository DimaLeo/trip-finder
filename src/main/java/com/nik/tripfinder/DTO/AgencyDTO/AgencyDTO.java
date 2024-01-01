package com.nik.tripfinder.DTO.AgencyDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AgencyDTO(
        String username,
        @JsonProperty("user_type")
        String userType,
        @JsonProperty("tax_code")
        String taxCode,
        @JsonProperty("brand_name")
        String brandName,
        String owner
) {}
