package com.nik.tripfinder.models;

import jakarta.persistence.*;

@Entity
@Table(name="customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    @Column(name = "tax_code", unique = true)
    private String taxCode;
    private String name;
    private String surname;
    @Column(name = "email", unique = true)
    private String email;

    public Customer() {

    }

    public Customer(User user, String taxCode, String name, String surname, String email) {
        this.user = user;
        this.taxCode = taxCode;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Customer(String taxCode, String name, String surname, String email) {
        this.taxCode = taxCode;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Customer(Integer id, User user, String taxCode, String name, String surname, String email) {
        this.id = id;
        this.user = user;
        this.taxCode = taxCode;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }


    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }
}
