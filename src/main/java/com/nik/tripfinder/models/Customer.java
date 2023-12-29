package com.nik.tripfinder.models;

import jakarta.persistence.*;

@Entity
@Table(name="customers")
public class Customer {

    @Id
    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    @Column(name = "tax_code")
    private String taxCode;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String password;

    public Customer(User user, String taxCode, String username, String name, String surname, String email, String password) {
        this.user = user;
        this.taxCode = taxCode;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public Customer(String taxCode, String username, String name, String surname, String email, String password) {
        this.taxCode = taxCode;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public Customer() {

    }

    public String getTaxCode() {
        return taxCode;
    }

    public String getUsername() {
        return username;
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

    public String getPassword() {
        return password;
    }
}
