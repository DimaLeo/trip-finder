package com.nik.tripfinder.DTO.ReservationDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public record ReservationDTO(
    Integer id,
    String name,
    String surname,
    String email,
    String destination,
    @JsonProperty("departure_date")
    Long startDate

) {
}
