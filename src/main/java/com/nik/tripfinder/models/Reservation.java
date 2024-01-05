package com.nik.tripfinder.models;

import jakarta.persistence.*;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reservationId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    public Reservation(){

    }

    public Reservation(Customer customer, Trip trip) {
        this.customer = customer;
        this.trip = trip;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Trip getTrip() {
        return trip;
    }
}
