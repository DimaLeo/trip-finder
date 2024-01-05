package com.nik.tripfinder;

import java.sql.Date;
import java.time.LocalDate;

import com.nik.tripfinder.payloads.requests.NewAgencyRequest;
import com.nik.tripfinder.payloads.requests.NewCustomerRequest;
import com.nik.tripfinder.services.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.nik.tripfinder.models.Trip;
import com.nik.tripfinder.services.TripsService;

@Configuration
public class PopulateDatabase implements CommandLineRunner {

    private final TripsService tripsService;
    private final AuthService authService;

    public PopulateDatabase(TripsService tripsService, AuthService authService) {
        this.tripsService = tripsService;
        this.authService = authService;
    }

    @Override
    public void run(String... args) throws Exception {
        
        Trip t1 = new Trip(1l, Date.valueOf(LocalDate.of(2024, 1, 3)), Date.valueOf(LocalDate.of(2024, 1, 3)), "Aristotelous Square", "Olympus mountain", "test", 45);
        tripsService.save(t1);
        Trip t2 = new Trip(2l, Date.valueOf(LocalDate.of(2024, 1, 6)), Date.valueOf(LocalDate.of(2024, 1, 8)), "Aristotelous Square", "Olympus mountain", "test", 45);
        tripsService.save(t2);
        Trip t3 = new Trip(3l, Date.valueOf(LocalDate.of(2024, 1, 12)), Date.valueOf(LocalDate.of(2024, 1, 15)), "Aristotelous Square", "Olympus mountain", "test", 45);
        tripsService.save(t3);

    }
    
}
