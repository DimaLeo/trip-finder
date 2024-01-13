package com.nik.tripfinder.services;

import com.nik.tripfinder.DTO.CustomerDTO.CustomerDTO;
import com.nik.tripfinder.DTO.CustomerDTO.CustomerDTOMapper;
import com.nik.tripfinder.DTO.TripDTO.TripDTO;
import com.nik.tripfinder.DTO.TripDTO.TripDTOMapper;
import com.nik.tripfinder.exceptions.GeneralException;
import com.nik.tripfinder.models.Customer;
import com.nik.tripfinder.models.Reservation;
import com.nik.tripfinder.models.Trip;
import com.nik.tripfinder.payloads.Objects.CustomerReservation;
import com.nik.tripfinder.payloads.requests.NewReservationRequest;
import com.nik.tripfinder.payloads.responses.CustomerReservationsResponse;
import com.nik.tripfinder.payloads.responses.ReservationsConfirmationResponse;
import com.nik.tripfinder.payloads.responses.TripReservationsResponse;
import com.nik.tripfinder.repositories.CustomersRepository;
import com.nik.tripfinder.repositories.ReservationRepository;
import com.nik.tripfinder.repositories.TripsRepository;

import jakarta.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final CustomersRepository customersRepository;
    private final TripsRepository tripsRepository;
    private final ReservationRepository reservationRepository;
    private final TripDTOMapper tripDTOMapper;
    private final CustomerDTOMapper customerDTOMapper;

    public ReservationService(CustomersRepository customersRepository, TripsRepository tripsRepository, ReservationRepository reservationRepository, TripDTOMapper tripDTOMapper, CustomerDTOMapper customerDTOMapper) {
        this.customersRepository = customersRepository;
        this.tripsRepository = tripsRepository;
        this.reservationRepository = reservationRepository;
        this.tripDTOMapper = tripDTOMapper;
        this.customerDTOMapper = customerDTOMapper;
    }

    private Customer retrieveReservationCustomer(Integer customerId) {

        Optional<Customer> customerOptional =
                customersRepository.findById(customerId);

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



    private Integer countTripReservations(Long id) {
        return reservationRepository.countByTripId(id);
    }

    public ReservationsConfirmationResponse createReservation(NewReservationRequest body) throws GeneralException {

        Customer dbCustomer;
        Trip dbTrip;
        Reservation dbReservation;

        try {
            dbCustomer = retrieveReservationCustomer(body.getCustomerId());
            if(dbCustomer == null) {
                throw new GeneralException("Something went wrong, customer does not exist", HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            if(e instanceof GeneralException && ((GeneralException) e).getStatus().equals(HttpStatus.NOT_FOUND)){
                throw e;
            }
            throw new GeneralException("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            dbTrip = retrieveReservationTrip(body.getTripId());
            if (dbTrip == null) {
                throw new GeneralException("The specified trip was not found", HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            if(e instanceof GeneralException && ((GeneralException) e).getStatus().equals(HttpStatus.NOT_FOUND)){
                throw e;
            }
            throw new GeneralException("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            dbReservation = retrieveReservation(dbCustomer.getUser().getId(), dbTrip.getId());
        }
        catch (Exception e) {
            throw new GeneralException("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (dbReservation != null){
            throw new GeneralException("Trip already reserved by the user", HttpStatus.CONFLICT);
        }

        try {
            Integer currentReservations = countTripReservations(dbTrip.getId());
            if(dbTrip.getMaxParticipants() <= currentReservations){
                throw new GeneralException("No available slots for this trip", HttpStatus.GONE);
            }

        }
        catch (Exception e) {
            if(e instanceof GeneralException && ((GeneralException) e).getStatus().equals(HttpStatus.GONE)){
             throw e;
            }
            throw new GeneralException("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);

        }

        Reservation newReservation = new Reservation(dbCustomer,dbTrip);

        try {
            dbReservation = reservationRepository.save(newReservation);
        }
        catch (Exception e) {
            throw new GeneralException("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ReservationsConfirmationResponse(
                "SUCCESS",
                "Successfully created reservation",
                HttpStatus.CREATED,
                dbReservation.getReservationId(),
                dbTrip.getId()
        );

    }

    public TripReservationsResponse getTripReservations(Long tripId) throws GeneralException {

        try {
            List<Reservation> reservations = reservationRepository.findReservationsByTripId(tripId);
            List<Integer> listOfId = new ArrayList<>();
            List<CustomerDTO> customers = reservations.
                    stream()
                    .map(reservation -> customerDTOMapper.apply(reservation.getCustomer())).toList();

            for(Reservation r: reservations){
                listOfId.add(r.getReservationId());
            }

            return new TripReservationsResponse(
                    "SUCCESS",
                    "Reservations successfully retrieved",
                    listOfId,
                    customers);
        }
        catch (Exception e){
            throw new GeneralException("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);

        }


    }

    public CustomerReservationsResponse getCustomerReservations(Integer customerId) throws GeneralException {

        try{
            List<Reservation> reservations = reservationRepository.findReservationsByCustomerCustomerId(customerId);
            List<CustomerReservation> reservationsList = new ArrayList<>();

            for(Reservation r: reservations){

                TripDTO tripDTO = tripDTOMapper.apply(r.getTrip());

                Integer currentParticipants = reservationRepository.countByTripId(tripDTO.getId());

                tripDTO.setCurrentParticipants(currentParticipants);
                reservationsList.add(


                        new CustomerReservation(
                                r.getReservationId(),
                                tripDTO

                        )
                );
            }

            return new CustomerReservationsResponse(
                    "SUCCESS",
                    "Reservations successfully retrieved",
                    reservationsList);
        }
        catch (Exception e) {
            throw new GeneralException("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);

        }



    }

    @Transactional
    public void cancelReservation(Integer reservationId) throws GeneralException {
        try {
            reservationRepository.deleteReservationByReservationId(reservationId);
             if (reservationRepository.existsReservationByReservationId(reservationId)) {
                 throw new GeneralException("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
             }
        }
        catch (Exception e ) {
            throw new GeneralException("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
