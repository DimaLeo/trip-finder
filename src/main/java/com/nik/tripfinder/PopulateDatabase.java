package com.nik.tripfinder;

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
        
        Trip t1 = new Trip(1l, 1705190400000l, 1705017600000l, "Thessaloniki", "Olympus mountain, Litochoro, Katerini", "<h1>test</h1><h2>test</h2><h3>test</h3><p><em>test</em></p>", 45);
        tripsService.save(t1);
        Trip t2 = new Trip(2l, 1705622400000l, 1705795200000l, "Athens", "Parnassos", "<h1>test</h1><h2>test</h2><h3>test</h3><p><em>test</em></p>", 50);
        tripsService.save(t2);
        Trip t3 = new Trip(3l, 1705708800000l, 1705795200000l, "Kavala", "Drama, Exochi, Falakro", "<h1>test</h1><h2>test</h2><h3>test</h3><p><em>test</em></p>", 32);
        tripsService.save(t3);

        NewCustomerRequest c1 = new NewCustomerRequest(
                "customer1",
                "12345678",
                "customer",
                "123456781",
                "Dimitris",
                "Leonidis",
                "dim@leon.gr"
        );

        authService.registerCustomer(c1);

        NewCustomerRequest c2 = new NewCustomerRequest(
                "customer2",
                "12345678",
                "customer",
                "123456782",
                "Dimitris",
                "Leonidis",
                "dim2@leon.gr"
        );

        authService.registerCustomer(c2);


    }
    
}
