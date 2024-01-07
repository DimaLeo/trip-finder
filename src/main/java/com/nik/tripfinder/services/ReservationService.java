package com.nik.tripfinder.services;

import com.nik.tripfinder.DTO.TripDTO.TripDTO;
import com.nik.tripfinder.DTO.TripDTO.TripDTOMapper;
import com.nik.tripfinder.models.Customer;
import com.nik.tripfinder.models.Reservation;
import com.nik.tripfinder.models.Trip;
import com.nik.tripfinder.payloads.Objects.CustomerReservation;
import com.nik.tripfinder.payloads.requests.NewReservationRequest;
import com.nik.tripfinder.payloads.responses.CustomerReservationsResponse;
import com.nik.tripfinder.payloads.responses.CustomerResponse;
import com.nik.tripfinder.payloads.responses.ReservationsConfirmationResponse;
import com.nik.tripfinder.payloads.responses.TripReservationsResponse;
import com.nik.tripfinder.repositories.CustomersRepository;
import com.nik.tripfinder.repositories.ReservationRepository;
import com.nik.tripfinder.repositories.TripsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final CustomersRepository customersRepository;
    private final TripsRepository tripsRepository;
    private final ReservationRepository reservationRepository;
    private final TripDTOMapper tripDTOMapper;

    public ReservationService(CustomersRepository customersRepository, TripsRepository tripsRepository, ReservationRepository reservationRepository, TripDTOMapper tripDTOMapper) {
        this.customersRepository = customersRepository;
        this.tripsRepository = tripsRepository;
        this.reservationRepository = reservationRepository;
        this.tripDTOMapper = tripDTOMapper;
    }

    private Customer retrieveReservationCustomer(String username){

        Optional<Customer> customerOptional =
                customersRepository.findCustomerByUserUsername(username);

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

    private Reservation retrieveReservation(Integer customerId, Long tripId){

        Optional<Reservation> reservationOptional =
                reservationRepository.findReservationByCustomerCustomerIdAndTripId(customerId, tripId);

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
        Reservation dbReservation;

        try{

            dbCustomer = retrieveReservationCustomer(body.getUsername());

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
            dbReservation = retrieveReservation(dbCustomer.getUser().getId(), dbTrip.getId());
        }
        catch (Exception e) {
            return new ReservationsConfirmationResponse(
                    "FAILED",
                    "Failed to check reservation existence in db\n" +
                            "message: " + e.getMessage()
            );
        }

        if(dbReservation!=null){
            return new ReservationsConfirmationResponse(
                    "FAILED",
                    "Trip already reserved by the user"
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
            dbReservation = reservationRepository.save(newReservation);
        }
        catch (Exception e) {
            return new ReservationsConfirmationResponse(
                    "FAILED",
                    "Failed to save reservation to db\n" +
                            "message: " + e.getMessage()
            );
        }


        return new ReservationsConfirmationResponse(
                "SUCCESS",
                "Successfully created reservation",
                dbReservation.getReservationId(),
                dbTrip.getId()

        );

    }

    public TripReservationsResponse getTripReservations(Long tripId) {

        List<Reservation> reservations = reservationRepository.findReservationsByTripId(tripId);
        List<Integer> listOfId = new ArrayList<>();

        for(Reservation r: reservations){
            listOfId.add(r.getReservationId());
        }

        return new TripReservationsResponse(
                "SUCCESS",
                "Reservations successfully retrieved",
                listOfId);

    }

    public CustomerReservationsResponse getCustomerReservations(Integer customerId) {

        List<Reservation> reservations = reservationRepository.findReservationsByCustomerCustomerId(customerId);
        List<CustomerReservation> reservationsList = new ArrayList<>();

        for(Reservation r: reservations){
            reservationsList.add(
                    new CustomerReservation(
                            r.getReservationId(),
                            tripDTOMapper.apply(r.getTrip())
                    )
            );
        }

        return new CustomerReservationsResponse(
                "SUCCESS",
                "Reservations successfully retrieved",
                reservationsList);

    }
}
