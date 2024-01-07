package com.nik.tripfinder.controllers;

import com.nik.tripfinder.payloads.responses.CustomerResponse;
import com.nik.tripfinder.services.CustomersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    private final CustomersService customersService;

    public CustomerController(CustomersService customersService) {
        this.customersService = customersService;
    }

    @GetMapping("/customer/{username}")
    public ResponseEntity<CustomerResponse> retrieveCustomer(@PathVariable String username){

        CustomerResponse responseBody = customersService.retrieveCustomer(username);

        return new ResponseEntity<>(responseBody, HttpStatus.OK);

    }

}
