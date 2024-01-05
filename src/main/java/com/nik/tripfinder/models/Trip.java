package com.nik.tripfinder.models;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "trips")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "start_date")
    private Long startDate;
    @Column(name = "end_date")
    private Long endDate;
    @Column(name = "departure_point")
    private String departurePoint;
    private String destination;
    @Column(name = "trip_schedule")
    private String tripSchedule;
    @Column(name = "max_participants")
    private Integer maxParticipants;
    // @ManyToOne
    // @JoinColumn(name = "tax_code")
    // private Agency agency;

    public Trip(Long id, Long startDate, Long endDate, String departurePoint, String destination, String tripSchedule, /*Agency agency,*/ Integer maxParticipants) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.departurePoint = departurePoint;
        this.destination = destination;
        this.tripSchedule = tripSchedule;
        // this.agency = agency;
        this.maxParticipants = maxParticipants;
    }

    public Trip() {

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

    public String getDeparturePoint() {
        return departurePoint;
    }

    public String getDestination() {
        return destination;
    }

    public String getTripSchedule() {
        return tripSchedule;
    }

    // public Agency getAgency() {
    //     return agency;
    // }

    public Integer getMaxParticipants() {
        return maxParticipants;
    }
}
