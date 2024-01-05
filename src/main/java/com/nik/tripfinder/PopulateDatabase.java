package com.nik.tripfinder;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.nik.tripfinder.models.Trip;
import com.nik.tripfinder.services.TripsService;

@Configuration
public class PopulateDatabase implements CommandLineRunner {

    private final TripsService tripsService;

    public PopulateDatabase(TripsService tripsService) {
        this.tripsService = tripsService;
    }

    @Override
    public void run(String... args) throws Exception {
        
        Trip t1 = new Trip(1l, 1705518000000l, 1705518000000l, "Aristotelous Square", "Olympus mountain", "test", 45);
        tripsService.save(t1);
        Trip t2 = new Trip(2l, 1705518000000l, 1705518000000l, "Aristotelous Square", "Olympus mountain", "test", 45);
        tripsService.save(t2);
        Trip t3 = new Trip(3l, 1705518000000l, 1705518000000l, "Aristotelous Square", "Olympus mountain", "test", 45);
        tripsService.save(t3);
    }
    
}
