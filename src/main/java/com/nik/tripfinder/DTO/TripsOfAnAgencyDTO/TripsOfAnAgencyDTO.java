package com.nik.tripfinder.DTO.TripsOfAnAgencyDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TripsOfAnAgencyDTO(
                @JsonProperty("start_date") Long startDate,
                @JsonProperty("end_date") Long endDate,
                @JsonProperty("departure_point") String departurePoint,
                String destination,
                @JsonProperty("trip_schedule") String tripSchedule,
                @JsonProperty("brand_name") String brandName,
                String owner,
                @JsonProperty("max_participants") Integer maxParticipants) {
}
