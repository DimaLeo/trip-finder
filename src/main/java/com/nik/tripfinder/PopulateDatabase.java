package com.nik.tripfinder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.nik.tripfinder.models.Agency;
import com.nik.tripfinder.models.Customer;
import com.nik.tripfinder.models.Reservation;
import com.nik.tripfinder.models.Trip;
import com.nik.tripfinder.models.User;
import com.nik.tripfinder.repositories.AgenciesRepository;
import com.nik.tripfinder.repositories.CustomersRepository;
import com.nik.tripfinder.repositories.ReservationRepository;
import com.nik.tripfinder.repositories.TripsRepository;
import com.nik.tripfinder.repositories.UserRepository;

import jakarta.transaction.Transactional;

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

                User u1 = new User(1, "customer1", encoder.encode("test"), "customer");
                User u2 = new User(2, "customer2", encoder.encode("test"), "customer");
                User u3 = new User(3, "agency1", encoder.encode("test"), "agency");
                User u4 = new User(4, "agency2", encoder.encode("test"), "agency");

                u1 = userRepository.save(u1);
                u2 = userRepository.save(u2);
                u3 = userRepository.save(u3);
                u4 = userRepository.save(u4);

                Customer c1 = new Customer(u1, "123456781", "Dimitris", "Leonidis", "dim@leon.gr");
                Customer c2 = new Customer( u2, "123456782", "Dimitris", "Leonidis", "dim1@leon.gr");

                customersRepository.save(c1);
                customersRepository.save(c2);

                Agency a1 = new Agency(u3, "123456783", "agency1", "Dima");
                Agency a2 = new Agency(u4, "123456784", "agency2", "Leon");

                agenciesRepository.save(a1);
                agenciesRepository.save(a2);

                Trip t1 = new Trip(1715817600000l,1715990400000l,  "Thessaloniki",
                                "Olympus mountain, Litochoro, Katerini",
                                "<p><strong><em>Day 1: Litochoro Arrival</em></strong></p><ul><li>10:00 AM: Depart from Thessaloniki Bus Station</li><li>11:30 AM: Enipeas Gorge</li><li>2:00 PM: Lunch in Litochoro</li><li>4:00 PM: Explore Litochoro</li><li>7:00 PM: Dinner with a view</li></ul><p><strong><em>Day 2: Mount Olympus Hike</em></strong></p><ul><li>8:00 AM: Breakfast</li><li>9:00 AM: Ascend Olympus</li><li>1:00 PM: Picnic lunch</li><li>4:00 PM: Reach Mytikas</li><li>7:00 PM: Descend, spa</li><li>9:00 PM: Dinner at a local gem</li></ul><p><strong><em>Day 3: Katerini and Departure</em></strong></p><ul><li>9:00 AM: Visit Katerini</li><li>12:00 PM: Seaside lunch</li><li>3:00 PM: Relax at Paralia Beach</li><li>6:00 PM: Depart to Thessaloniki Bus Station</li></ul>",
                                 45, a1);
                Trip t2 = new Trip(1716422400000l,1716422400000l,  "Athens", "Parnassos",
                                "<p><strong>Departure from Athens</strong></p><ul><li>8:00 AM: Depart from Athens Central Bus Station</li></ul><p><strong>Day Itinerary</strong></p><ul><li>10:30 AM: Arrival in Parnassos</li><li>11:30 AM: Explore the ancient Delphi site</li><li>1:30 PM: Lunch with a view</li><li>3:00 PM: Visit the Delphi Archaeological Museum</li><li>5:00 PM: Scenic drive through Parnassos National Park</li><li>7:00 PM: Dinner at a traditional taverna</li><li>9:00 PM: Departure to Athens</li></ul><p><strong style=\"color: var(--tw-prose-bold);\"><em>Join us for a memorable day on Mount Parnassos!</em></strong> 🏛️</p>",
                                 50, a2);
                Trip t3 = new Trip(1716422400000l,1716595200000l,  "Kavala", "Drama, Exochi, Falakro",
                                "<p><strong><em>Day 1: Drama Delight</em></strong></p><ul><li>10:00 AM: Depart from Kavala Bus Station</li><li>11:30 AM: Arrival in Drama</li><li>2:00 PM: Lunch in Drama</li><li>4:00 PM: Explore the scenic Exochi Village</li><li>7:00 PM: Dinner with a mountain view</li></ul><p><strong><em>Day 2: Exochi Exploration</em></strong></p><ul><li>8:00 AM: Breakfast</li><li>9:00 AM: Discover Exochi's charm</li><li>1:00 PM: Lunch at a local spot</li><li>4:00 PM: Free time to explore</li><li>7:00 PM: Dinner at a cozy tavern</li></ul><p><strong><em>Day 3: Mount Falakro Ascent</em></strong></p><ul><li>9:00 AM: Breakfast</li><li>10:00 AM: Depart for Mount Falakro</li><li>1:00 PM: Summit and picnic lunch</li><li>4:00 PM: Descend and depart for Kavala</li><li>7:00 PM: Arrival in Kavala</li></ul>",
                                 32, a1);

                tripsRepository.save(t1);
                tripsRepository.save(t2);
                tripsRepository.save(t3);

                Reservation r1 = new Reservation(c1, t1);
                Reservation r2 = new Reservation(c2, t1);
                Reservation r3 = new Reservation(c1, t2);
                Reservation r4 = new Reservation(c2, t2);
                // Reservation r5 = new Reservation(c1, t3);
                // Reservation r6 = new Reservation(c2, t3);

                reservationRepository.save(r1);
                reservationRepository.save(r2);
                reservationRepository.save(r3);
                reservationRepository.save(r4);
                // reservationRepository.save(r5);
                // reservationRepository.save(r6);
        }

}
