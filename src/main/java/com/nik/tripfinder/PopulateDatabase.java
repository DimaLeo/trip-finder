package com.nik.tripfinder;

import com.nik.tripfinder.models.*;
import com.nik.tripfinder.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PopulateDatabase implements CommandLineRunner {

        private final TripsRepository tripsRepository;
        private final CustomersRepository customersRepository;
        private final AgenciesRepository agenciesRepository;
        private final UserRepository userRepository;
        private final ReservationRepository reservationRepository;
        private final BCryptPasswordEncoder encoder;

        public PopulateDatabase(TripsRepository tripsRepository, CustomersRepository customersRepository,
                        AgenciesRepository agenciesRepository, UserRepository userRepository,
                        ReservationRepository reservationRepository) {
                this.tripsRepository = tripsRepository;
                this.customersRepository = customersRepository;
                this.agenciesRepository = agenciesRepository;
                this.userRepository = userRepository;
                this.reservationRepository = reservationRepository;
                encoder = new BCryptPasswordEncoder();
        }

        @Override
        @Transactional
        public void run(String... args) throws Exception {

                User u1 = new User(1, "customer1", encoder.encode("12345678"), "customer");
                User u2 = new User(2, "customer2", encoder.encode("12345678"), "customer");
                User u3 = new User(3, "agency1", encoder.encode("12345678"), "agency");
                User u4 = new User(4, "agency2", encoder.encode("12345678"), "agency");

                u1 = userRepository.save(u1);
                u2 = userRepository.save(u2);
                u3 = userRepository.save(u3);
                u4 = userRepository.save(u4);

                Customer c1 = new Customer(
                                u1,
                                "123456781",
                                "Dimitris",
                                "Leonidis",
                                "dim@leon.gr");

                Customer c2 = new Customer(
                                u2,
                                "123456782",
                                "Dimitris",
                                "Leonidis",
                                "dim1@leon.gr");

                customersRepository.save(c1);
                customersRepository.save(c2);

                Agency a1 = new Agency(
                                u3,
                                "123456783",
                                "agency1",
                                "Dima");

                Agency a2 = new Agency(
                                u4,
                                "123456784",
                                "agency2",
                                "Leon");

                agenciesRepository.save(a1);
                agenciesRepository.save(a2);

                Trip t1 = new Trip(1705190400000l, 1705017600000l, "Thessaloniki",
                                "Olympus mountain, Litochoro, Katerini",
                                "<h1>test</h1><h2>test</h2><h3>test</h3><p><em>test</em></p>", 45, a1);
                Trip t2 = new Trip(1705622400000l, 1705795200000l, "Athens", "Parnassos",
                                "<h1>test</h1><h2>test</h2><h3>test</h3><p><em>test</em></p>", 50, a2);
                Trip t3 = new Trip(1705708800000l, 1705795200000l, "Kavala", "Drama, Exochi, Falakro",
                                "<h1>test</h1><h2>test</h2><h3>test</h3><p><em>test</em></p>", 32, a1);
                tripsRepository.save(t1);
                tripsRepository.save(t2);
                tripsRepository.save(t3);

                Reservation r1 = new Reservation(c1, t1);
                Reservation r2 = new Reservation(c2, t1);
                Reservation r3 = new Reservation(c1, t2);
                Reservation r4 = new Reservation(c2, t2);
                Reservation r5 = new Reservation(c1, t3);
                Reservation r6 = new Reservation(c2, t3);

                reservationRepository.save(r1);
                reservationRepository.save(r2);
                reservationRepository.save(r3);
                reservationRepository.save(r4);
                reservationRepository.save(r5);
                reservationRepository.save(r6);

        }

}
