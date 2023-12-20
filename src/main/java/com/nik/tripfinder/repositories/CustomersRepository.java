package com.nik.tripfinder.repositories;

import com.nik.tripfinder.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepository extends JpaRepository<Customer,String> {
}
