package com.nik.tripfinder.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    @Column(name = "departure_area", nullable = false)
    private String departureArea;
    @Column(nullable = false)
    private String destination;
    // medium text because the schedule is big and cannot fit in a varchar column
    @Column(name = "trip_schedule", columnDefinition = "MEDIUMTEXT", nullable = false)
    private String tripSchedule;
    @Column(name = "max_participants", nullable = false)
    private Integer maxParticipants;

    @ManyToOne
    @JoinColumn(name = "agency_id")
    // Add the agency to the returned Trip Entity
    private Agency agency;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    // Don't add it to the returned Trip Entity
    @JsonBackReference
    private List<Reservation> reservations;

    // It will not be stored in the database
    @Transient
    @JsonProperty("reservations_number")
    private Integer reservationsNumber;

    public Trip(Long startDate, Long endDate, String departureArea, String destination, String tripSchedule,
            Integer maxParticipants, Agency agency) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.departureArea = departureArea;
        this.destination = destination;
        this.tripSchedule = tripSchedule;
        this.maxParticipants = maxParticipants;
        this.agency = agency;
    }

    public Trip(Long startDate, Long endDate, String departureArea, String destination, String tripSchedule,
            Integer maxParticipants) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.departureArea = departureArea;
        this.destination = destination;
        this.tripSchedule = tripSchedule;
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

    public String getDepartureArea() {
        return departureArea;
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

    public List<Reservation> getReservations() {
        return reservations;
    }

    public Integer getReservationsNumber() {
        if (reservations != null) {
            return reservations.size();
        } else {
            return 0;
        }
    }
}
