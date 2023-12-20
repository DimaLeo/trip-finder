package com.nik.tripfinder.models;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "trips")
public class Trip {

    @Id
    private String id;
    @Column("start_date")
    private Date startDate;
    @Column("end_date")
    private Date endDate;
    @Column(name = "departure_point")
    private String departurePoint;
    private String destination;
    @Column(name = "trip_schedule")
    private String tripSchedule;
    @ManyToOne
    @JoinColumn(name = "tax_code")
    private Agency agency;
    @Column(name = "max_participatns")
    private Integer maxParticipants;

    public Trip(String id, Date startDate, Date endDate, String departurePoint, String destination, String tripSchedule, Agency agency, Integer maxParticipants) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.departurePoint = departurePoint;
        this.destination = destination;
        this.tripSchedule = tripSchedule;
        this.agency = agency;
        this.maxParticipants = maxParticipants;
    }

    public Trip() {

    }

    public String getId() {
        return id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
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

    public Agency getAgency() {
        return agency;
    }

    public Integer getMaxParticipants() {
        return maxParticipants;
    }
}
