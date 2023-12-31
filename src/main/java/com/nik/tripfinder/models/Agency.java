package com.nik.tripfinder.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "agencies")
public class Agency {
    @Id
    @Column(name = "tax_code")
    private String taxCode;
    private String username;
    @Column(name = "brand_name")
    private String brandName;
    private String owner;
    private String password;

    public Agency(String taxCode, String username, String brandName, String owner, String password) {
        this.taxCode = taxCode;
        this.username = username;
        this.brandName = brandName;
        this.owner = owner;
        this.password = password;
    }

    public Agency() {
    }

    @JsonIgnore
    @OneToMany(mappedBy="agency", 
		       cascade= CascadeType.ALL,
		       fetch = FetchType.LAZY)
	private List<Trip> trips = new ArrayList<Trip>();

    public String getTaxCode() {
        return this.taxCode;
    }

    public String getUsername() {
        return this.username;
    }

    public String getBrandName() {
        return this.brandName;
    }

    public String getOwner() {
        return this.owner;
    }

    public String getPassword() {
        return this.password;
    }
    
    public List<Trip> getTrips() {return this.trips;}
}
