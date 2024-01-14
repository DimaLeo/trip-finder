package com.nik.tripfinder.payloads.requests;

import com.nik.tripfinder.models.Trip;

public class NewTripRequest {

    // private Long startDate;
    // private Long endDate;
    // private String departureArea;
    // private String destination;
    // private String tripSchedule;
    // private Integer maxParticipants;

    private Trip trip;
    private Integer agencyId;

    // public NewTripRequest(Long startDate, Long endDate, String departureArea, String destination, String tripSchedule,
    //         Integer maxParticipants, Integer agencyId) {
    //     this.startDate = startDate;
    //     this.endDate = endDate;
    //     this.departureArea = departureArea;
    //     this.destination = destination;
    //     this.tripSchedule = tripSchedule;
    //     this.maxParticipants = maxParticipants;
    //     this.agencyId = agencyId;
    // }

    public NewTripRequest(Trip trip, Integer agencyId) {
        this.trip = trip;
        this.agencyId = agencyId;
    }

    // public Long getStartDate() {
    //     return startDate;
    // }

    // public Long getEndDate() {
    //     return endDate;
    // }

    // public String getDepartureArea() {
    //     return departureArea;
    // }

    // public String getDestination() {
    //     return destination;
    // }

    // public String getTripSchedule() {
    //     return tripSchedule;
    // }

    // public Integer getMaxParticipants() {
    //     return maxParticipants;
    // }

    public Trip getTrip() {
        return trip;
    }

    public Integer getAgencyId() {
        return agencyId;
    }

}
