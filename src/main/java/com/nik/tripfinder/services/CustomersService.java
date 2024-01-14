package com.nik.tripfinder.services;

import com.nik.tripfinder.DTO.CustomerDTO.CustomerDTO;
import com.nik.tripfinder.DTO.CustomerDTO.CustomerDTOMapper;
import com.nik.tripfinder.DTO.ReservationDTO.ReservationDTO;
import com.nik.tripfinder.DTO.ReservationDTO.ReservationDTOMapper;
import com.nik.tripfinder.exceptions.GeneralException;
import com.nik.tripfinder.models.Customer;
import com.nik.tripfinder.repositories.CustomersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomersService {

    private final CustomersRepository customersRepository;
    private final CustomerDTOMapper customerDTOMapper;
    private final ReservationDTOMapper reservationDTOMapper;

    public CustomersService(CustomersRepository customersRepository, CustomerDTOMapper customerDTOMapper, ReservationDTOMapper reservationDTOMapper) {
        this.customersRepository = customersRepository;
        this.customerDTOMapper = customerDTOMapper;
        this.reservationDTOMapper = reservationDTOMapper;
    }

    public CustomerDTO getCustomerById(Integer id) throws GeneralException {
        try {
            // Check if there is a customer with the given id
            Optional<Customer> dbCustomer = customersRepository.findById(id);
            if (dbCustomer.isPresent()) {
                    return customerDTOMapper.apply(dbCustomer.get());
            } else {
                throw new GeneralException("There is no customer with id " + id, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ReservationDTO> getReservations(Integer customerId) throws GeneralException {
        try {
            // Check if there is a customer with the given id
            Optional<Customer> optionalCustomer = customersRepository.findById(customerId);
            if (optionalCustomer.isPresent()) {
                return reservationDTOMapper.mapToDTOList(optionalCustomer.get().getReservations());
            } else throw new GeneralException("There is no customer with id " + customerId, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw e;
        }
    }
}
