package com.nik.tripfinder.DTO.TripDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nik.tripfinder.DTO.AgencyDTO.MinimalAgencyDTO;
import org.springframework.lang.Nullable;

public class TripDTO{
        private Long id;
        @JsonProperty("start_date")
        private Long startDate;
        @JsonProperty("end_date")
        private Long endDate;
        @JsonProperty("departure_area")
        private String departureArea;
        private String destination;
        @JsonProperty("trip_schedule")
        private String tripSchedule;
        @JsonProperty("max_participants")
        private Integer maxParticipants;
        @JsonProperty("agency")
        private MinimalAgencyDTO agency;
        @Nullable
        @JsonProperty("reservation_id")
        private Integer reservationId;
        @Nullable
        @JsonProperty("current_participants")
        private Integer currentParticipants;

        public TripDTO(Long id, Long startDate, Long endDate, String departureArea, String destination, String tripSchedule, Integer maxParticipants, MinimalAgencyDTO agency) {
                this.id = id;
                this.startDate = startDate;
                this.endDate = endDate;
                this.departureArea = departureArea;
                this.destination = destination;
                this.tripSchedule = tripSchedule;
                this.maxParticipants = maxParticipants;
                this.agency = agency;
        }

        public void setReservationId(Integer reservationId) {
                this.reservationId = reservationId;
        }

        public Long getId() {
                return id;
        }

        public Long getStartDate() {
                return startDate;
        }

        public Long getEndDate() {
                return endDate;
        }

        public String getDepartureArea() {
                return departureArea;
        }

        public String getDestination() {
                return destination;
        }

        public String getTripSchedule() {
                return tripSchedule;
        }

        public Integer getMaxParticipants() {
                return maxParticipants;
        }

        public MinimalAgencyDTO getAgency() {
                return agency;
        }

        @Nullable
        public Integer getReservationId() {
                return reservationId;
        }

        public Integer getCurrentParticipants() {
                return currentParticipants;
        }

        public void setCurrentParticipants(Integer currentParticipants) {
                this.currentParticipants = currentParticipants;
        }
}
