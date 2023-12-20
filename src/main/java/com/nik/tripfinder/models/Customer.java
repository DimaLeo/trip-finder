package com.nik.tripfinder.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="customers")
public class Customer {

    @Id
    @Column(name = "tax_code")
    private String taxCode;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String password;

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
