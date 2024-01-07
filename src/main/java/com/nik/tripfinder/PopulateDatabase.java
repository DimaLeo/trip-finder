package com.nik.tripfinder;

import com.nik.tripfinder.models.*;
import com.nik.tripfinder.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.nik.tripfinder.services.TripsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PopulateDatabase implements CommandLineRunner {

    private final TripsService tripsService;
    private final CustomersRepository customersRepository;
    private final AgenciesRepository agenciesRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final BCryptPasswordEncoder encoder;

    public PopulateDatabase(TripsService tripsService, CustomersRepository customersRepository, AgenciesRepository agenciesRepository, UserRepository userRepository, ReservationRepository reservationRepository) {
        this.tripsService = tripsService;
        this.customersRepository = customersRepository;
        this.agenciesRepository = agenciesRepository;
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
        encoder = new BCryptPasswordEncoder();
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        Trip t1 = new Trip(1l, 1705518000000l, 1705518000000l, "Aristotelous Square", "Olympus mountain", "test", 45);
        tripsService.save(t1);
        Trip t2 = new Trip(2l, 1705518000000l, 1705518000000l, "Aristotelous Square", "Olympus mountain", "test", 45);
        tripsService.save(t2);
        Trip t3 = new Trip(3l, 1705518000000l, 1705518000000l, "Aristotelous Square", "Olympus mountain", "test", 45);
        tripsService.save(t3);

        User u1 = new User(
                1,
                "customer1",
                encoder.encode("12345678"),
                "customer"
        );

        User u2 = new User(
                2,
                "customer2",
                encoder.encode("12345678"),
                "customer"
        );

        User u3 = new User(
                3,
                "agency1",
                encoder.encode("12345678"),
                "agency"
        );

        User u4 = new User(
                4,
                "agency2",
                encoder.encode("12345678"),
                "agency"
        );

        u1 = userRepository.save(u1);
        u2 = userRepository.save(u2);
        u3 = userRepository.save(u3);
        u4 = userRepository.save(u4);

        Customer c1 = new Customer(
                u1,
                "123456781",
                "Dimitris",
                "Leonidis",
                "dim@leon.gr"
        );

        Customer c2 = new Customer(
                u2,
                "123456782",
                "Dimitris",
                "Leonidis",
                "dim1@leon.gr"
        );

        customersRepository.save(c1);
        customersRepository.save(c2);

        Agency a1 = new Agency(
                u3,
                "123456783",
                "agency1",
                "Dima"
        );

        Agency a2 = new Agency(
                u4,
                "123456784",
                "agency2",
                "Leon"
        );

        agenciesRepository.save(a1);
        agenciesRepository.save(a2);

        Reservation r1 = new Reservation(
                c1,
                t1
        );Reservation r2 = new Reservation(
                c2,
                t1
        );
        Reservation r3 = new Reservation(
                c1,
                t2
        );Reservation r4 = new Reservation(
                c2,
                t2
        );
        Reservation r5 = new Reservation(
                c1,
                t3
        );Reservation r6 = new Reservation(
                c2,
                t3
        );

        reservationRepository.save(r1);
        reservationRepository.save(r2);
        reservationRepository.save(r3);
        reservationRepository.save(r4);
        reservationRepository.save(r5);
        reservationRepository.save(r6);


    }

}
