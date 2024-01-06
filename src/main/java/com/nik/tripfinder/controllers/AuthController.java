package com.nik.tripfinder.controllers;

import com.nik.tripfinder.payloads.requests.AuthenticationRequest;
import com.nik.tripfinder.payloads.requests.NewAgencyRequest;
import com.nik.tripfinder.payloads.requests.NewCustomerRequest;
import com.nik.tripfinder.payloads.responses.AgencyResponse;
import com.nik.tripfinder.payloads.responses.CustomerResponse;
import com.nik.tripfinder.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/agency-registration")
    public ResponseEntity<AgencyResponse> registerAgency(@RequestBody NewAgencyRequest newAgencyRequest) {

        AgencyResponse agencyResponse = authService.registerAgency(newAgencyRequest);

        return new ResponseEntity<>(agencyResponse, HttpStatus.OK);

    }

    @PostMapping("/customer-registration")
    public ResponseEntity<CustomerResponse> registerCustomer(@RequestBody NewCustomerRequest newCustomerRequest) {

        CustomerResponse newCustomerResponse = authService.registerCustomer(newCustomerRequest);

        return new ResponseEntity<>(newCustomerResponse, HttpStatus.OK);

    }

    @PostMapping("/agency-authentication")
    public ResponseEntity<AgencyResponse> authenticateAgency(@RequestBody AuthenticationRequest body) {

        AgencyResponse authenticatedAgency = authService.authenticateAgency(body);

        return new ResponseEntity<>(authenticatedAgency, HttpStatus.OK);

    }

    @PostMapping("/customer-authentication")
    public ResponseEntity<CustomerResponse> authenticateCustomer(@RequestBody AuthenticationRequest body) {

        CustomerResponse authenticatedCustomer = authService.authenticateCustomer(body);

        return new ResponseEntity<>(authenticatedCustomer, HttpStatus.OK);

    }
}
