package com.nik.tripfinder.controllers;

import com.nik.tripfinder.DTO.CustomerDTO.CustomerDTO;
import com.nik.tripfinder.DTO.ReservationDTO.ReservationDTO;
import com.nik.tripfinder.exceptions.GeneralException;
import com.nik.tripfinder.services.CustomersService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomersService customersService;

    public CustomerController(CustomersService customersService) {
        this.customersService = customersService;
    }

    // Get customer details
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Integer id) throws GeneralException {
        return new ResponseEntity<>(customersService.getCustomerById(id), HttpStatus.OK);
    }

    // Get customer's reservations
    @GetMapping("/{id}/reservations")
    public List<ReservationDTO> getAllReservations(@PathVariable(name = "id") Integer customerId) throws GeneralException {
        return customersService.getReservations(customerId);
    }

}
