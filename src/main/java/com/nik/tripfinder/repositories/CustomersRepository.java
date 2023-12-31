package com.nik.tripfinder.repositories;

import com.nik.tripfinder.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomersRepository extends JpaRepository<Customer,String> {

    Optional<Customer> findCustomerByEmailOrTaxCode(String email, String taxCode);
    Optional<Customer> findCustomerById(Integer id);

}
