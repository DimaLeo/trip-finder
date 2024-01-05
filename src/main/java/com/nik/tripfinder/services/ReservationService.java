package com.nik.tripfinder.services;

import com.nik.tripfinder.DTO.ReservationDTO.ReservationDTOMapper;
import com.nik.tripfinder.models.Customer;
import com.nik.tripfinder.models.Reservation;
import com.nik.tripfinder.models.Trip;
import com.nik.tripfinder.payloads.requests.NewReservationRequest;
import com.nik.tripfinder.payloads.responses.ReservationsConfirmationResponse;
import com.nik.tripfinder.repositories.CustomersRepository;
import com.nik.tripfinder.repositories.ReservationRepository;
import com.nik.tripfinder.repositories.TripsRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class ReservationService {

    private final CustomersRepository customersRepository;
    private final TripsRepository tripsRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationDTOMapper reservationDTOMapper;

    public ReservationService(CustomersRepository customersRepository, TripsRepository tripsRepository, ReservationRepository reservationRepository, ReservationDTOMapper reservationDTOMapper) {
        this.customersRepository = customersRepository;
        this.tripsRepository = tripsRepository;
        this.reservationRepository = reservationRepository;
        this.reservationDTOMapper = reservationDTOMapper;
    }

    private Customer retrieveReservationCustomer(String email){

        Optional<Customer> customerOptional =
                customersRepository.findCustomerByEmail(email);

        if(customerOptional.isPresent()){
            return customerOptional.get();
        }
        else{
            return null;
        }



    }

    private Trip retrieveReservationTrip(Long id){

        Optional<Trip> tripOptional =
                tripsRepository.findById(id);

        if(tripOptional.isPresent()){
            return tripOptional.get();
        }
        else{
            return null;
        }
    }

    private Reservation retrieveReservation(Long customerId, Long tripId){

        Optional<Reservation> reservationOptional =
                reservationRepository.findReservationByCustomerIdAndTripId(customerId, tripId);

        if(reservationOptional.isPresent()){
            return reservationOptional.get();
        }
        else{
            return null;
        }
    }



    private Long countTripReservations(Long id){
        return reservationRepository.countByTripId(id);
    }

    public ReservationsConfirmationResponse createReservation(NewReservationRequest body) {

        Customer dbCustomer;
        Trip dbTrip;

        try{

            dbCustomer = retrieveReservationCustomer(body.getEmail());

            if(dbCustomer == null){
                return new ReservationsConfirmationResponse(
                        "FAILED",
                        "Something went wrong, customer does not exist"
                );
            }
        }
        catch (Exception e) {
            return new ReservationsConfirmationResponse(
                    "FAILED",
                    "Failed to retrieve customer from db\n" +
                            "message: " + e.getMessage()
            );
        }

        try{

            dbTrip = retrieveReservationTrip(body.getTripId());

            if(dbTrip == null){
                return new ReservationsConfirmationResponse(
                        "FAILED",
                        "Something went wrong, trip does not exist"
                );
            }
        }
        catch (Exception e) {
            return new ReservationsConfirmationResponse(
                    "FAILED",
                    "Failed to retrieve trip from db\n" +
                            "message: " + e.getMessage()
            );
        }


        try {
            Long takenSlots = countTripReservations(dbTrip.getId());
            System.out.println(takenSlots);
            if(dbTrip.getMaxParticipants() <= takenSlots){
                return new ReservationsConfirmationResponse(
                        "FAILED",
                        "No available slots for this trip"
                );
            }

        }
        catch (Exception e){

            return new ReservationsConfirmationResponse(
                    "FAILED",
                    "Failed to retrieve available slots for the trip\n"+
                            "message: " + e.getMessage()
            );

        }



        Reservation newReservation = new Reservation(dbCustomer,dbTrip);

        try{
            reservationRepository.save(newReservation);
        }
        catch (Exception e) {
            return new ReservationsConfirmationResponse(
                    "FAILED",
                    "Failed to save reservation to db\n" +
                            "message: " + e.getMessage()
            );
        }

        Reservation dbReservation = retrieveReservation(dbCustomer.getId())

        try {
            reservationRepository.findReservationByCustomerId()
        }

        return new ReservationsConfirmationResponse(
                "SUCCESS",
                "Successfully created reservation"
        );

    }
}
