package com.nik.tripfinder.models;

import jakarta.persistence.*;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    private String id;

    @OneToOne
    @JoinColumn(name = "customer_tax_code")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;
}
