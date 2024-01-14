package com.nik.tripfinder.services;

import com.nik.tripfinder.exceptions.GeneralException;
import com.nik.tripfinder.models.Customer;
import com.nik.tripfinder.models.Reservation;
import com.nik.tripfinder.models.Trip;
import com.nik.tripfinder.payloads.requests.NewReservationRequest;
import com.nik.tripfinder.repositories.CustomersRepository;
import com.nik.tripfinder.repositories.ReservationRepository;
import com.nik.tripfinder.repositories.TripsRepository;

import jakarta.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationService {

    private final CustomersRepository customersRepository;
    private final TripsRepository tripsRepository;
    private final ReservationRepository reservationRepository;

    public ReservationService(CustomersRepository customersRepository, TripsRepository tripsRepository, ReservationRepository reservationRepository) {
        this.customersRepository = customersRepository;
        this.tripsRepository = tripsRepository;
        this.reservationRepository = reservationRepository;
    }

    private Customer retrieveReservationCustomer(Integer customerId) {
        Optional<Customer> customerOptional = customersRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            return customerOptional.get();
        } else {
            return null;
        }
    }

    private Trip retrieveReservationTrip(Long id){
        Optional<Trip> tripOptional = tripsRepository.findById(id);
        if (tripOptional.isPresent()) {
            return tripOptional.get();
        } else{
            return null;
        }
    }

    private Reservation retrieveReservation(Integer customerId, Long tripId){
        Optional<Reservation> reservationOptional =
                reservationRepository.findReservationByCustomerCustomerIdAndTripId(customerId, tripId);
        if (reservationOptional.isPresent()) {
            return reservationOptional.get();
        } else{
            return null;
        }
    }

    public Reservation createReservation(NewReservationRequest body) throws GeneralException {
        try {
            Customer dbCustomer;
            Trip dbTrip;
            Reservation dbReservation;

            // Check if the customer exists
            dbCustomer = retrieveReservationCustomer(body.getCustomerId());
            if (dbCustomer == null) {
                throw new GeneralException("There is no customer with id " + body.getCustomerId(), HttpStatus.BAD_REQUEST);
            }

            // Check if the trip exists
            dbTrip = retrieveReservationTrip(body.getTripId());
            if (dbTrip == null) {
                throw new GeneralException("There is no trip with id " + body.getCustomerId(), HttpStatus.BAD_REQUEST);
            }

            // Check if the reservation already exists
            dbReservation = retrieveReservation(dbCustomer.getUser().getId(), dbTrip.getId());
            if (dbReservation != null){
                throw new GeneralException("Trip already reserved by the user", HttpStatus.CONFLICT);
            }

            // Check availability
            if(dbTrip.getMaxParticipants() <= dbTrip.getReservationsNumber()){
                throw new GeneralException("No available slots for this trip", HttpStatus.GONE);
            }

            Reservation newReservation = new Reservation(dbCustomer,dbTrip);
            dbReservation = reservationRepository.save(newReservation);

            return dbReservation;
        }
        catch (Exception e){
            if (e instanceof GeneralException) throw e;
            throw new GeneralException("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Transactional
    public void cancelReservation(Integer reservationId) throws GeneralException {
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationId);
        if (reservationOptional.isPresent()) {
            reservationRepository.delete(reservationOptional.get());
        } else {
            throw new GeneralException("There is no reservation with id" + reservationId, HttpStatus.NOT_FOUND);
        }
    }
}
