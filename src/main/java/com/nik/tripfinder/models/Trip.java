package com.nik.tripfinder.models;

import jakarta.persistence.*;

@Entity
@Table(name = "trips")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "start_date", nullable = false)
    private Long startDate;
    @Column(name = "end_date", nullable = false)
    private Long endDate;
    @Column(name = "departure_point", nullable = false)
    private String departurePoint;
    @Column(nullable = false)
    private String destination;
    @Column(name = "trip_schedule", columnDefinition = "MEDIUMTEXT", nullable = false)
    private String tripSchedule;
    @Column(name = "max_participants", nullable = false)
    private Integer maxParticipants;
    @ManyToOne
    @JoinColumn(name = "agency_id")
    private Agency agency;

    public Trip(Long id, Long startDate, Long endDate, String departurePoint, String destination, String tripSchedule,
            Agency agency, Integer maxParticipants) {
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

    public Agency getAgency() {
        return agency;
    }

    public Integer getMaxParticipants() {
        return maxParticipants;
    }
}
