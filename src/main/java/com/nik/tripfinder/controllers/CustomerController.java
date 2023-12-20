package com.nik.tripfinder.controllers;

import com.nik.tripfinder.services.CustomersService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    private final CustomersService customersService;

    public CustomerController(CustomersService customersService) {
        this.customersService = customersService;
    }
}
