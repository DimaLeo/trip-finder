package com.nik.tripfinder.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "agencies")
public class Agency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "tax_code", unique = true)
    private String taxCode;
    @Column(name = "brand_name", unique = true)
    private String brandName;
    private String owner;

    @OneToMany(mappedBy = "agency", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Trip> trips;

    public Agency(User user, String taxCode, String brandName, String owner) {
        this.user = user;
        this.taxCode = taxCode;
        this.brandName = brandName;
        this.owner = owner;
    }

    public Agency(String taxCode, String brandName, String owner) {
        this.taxCode = taxCode;
        this.brandName = brandName;
        this.owner = owner;
    }

    public Agency() {
    }

    public Integer getId() {
        return this.id;
    }

    public User getUser() {
        return this.user;
    }

    public String getTaxCode() {
        return this.taxCode;
    }

    public String getBrandName() {
        return this.brandName;
    }

    public String getOwner() {
        return this.owner;
    }

    public List<Trip> getTrips() {
        return trips;
    }
}
