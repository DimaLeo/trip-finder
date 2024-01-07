package com.nik.tripfinder.repositories;

import com.nik.tripfinder.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomersRepository extends JpaRepository<Customer,Integer> {

    Optional<Customer> findCustomerByEmailOrTaxCode(String email, String taxCode);
    Optional<Customer> findCustomerById(Integer id);

    @Query("SELECT c FROM Customer c WHERE c.user.username = :username")
    Optional<Customer> findByUserUsername(@Param("username") String username);
    Optional<Customer> findCustomerByUserUsername(String username);


}
