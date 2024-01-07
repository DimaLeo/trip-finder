package com.nik.tripfinder.models;

import jakarta.persistence.*;

@Entity
@Table(name = "agencies")
public class Agency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer agencyId;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "tax_code", unique = true)
    private String taxCode;
    @Column(name = "brand_name", unique = true)
    private String brandName;
    private String owner;

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

    public Agency(Integer agencyId, String taxCode, String brandName, String owner) {
        this.agencyId = agencyId;
        this.taxCode = taxCode;
        this.brandName = brandName;
        this.owner = owner;
    }

    public Agency() {
    }


    public User getUser() {return this.user;}
    public String getTaxCode() {
        return this.taxCode;
    }

    public String getBrandName() {
        return this.brandName;
    }

    public String getOwner() {
        return this.owner;
    }
}
