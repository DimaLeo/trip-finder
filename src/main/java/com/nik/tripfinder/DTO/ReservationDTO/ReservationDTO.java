package com.nik.tripfinder.DTO.ReservationDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nik.tripfinder.DTO.TripDTO.TripDTO;

public record ReservationDTO (
    @JsonProperty("reservation_id")
    Integer reservationId,
    TripDTO trip
) {}
