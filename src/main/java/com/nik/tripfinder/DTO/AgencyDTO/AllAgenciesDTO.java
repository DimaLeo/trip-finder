package com.nik.tripfinder.DTO.AgencyDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AllAgenciesDTO(
        @JsonProperty("brand_name") String brandName,
        String owner) {
}
