package com.nik.tripfinder.services;

import com.nik.tripfinder.DTO.CustomerDTO.CustomerDTO;
import com.nik.tripfinder.DTO.CustomerDTO.CustomerDTOMapper;
import com.nik.tripfinder.exceptions.GeneralException;
import com.nik.tripfinder.models.Customer;
import com.nik.tripfinder.payloads.responses.CustomerResponse;
import com.nik.tripfinder.repositories.CustomersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomersService {

    private final CustomersRepository customersRepository;
    private final CustomerDTOMapper customerDTOMapper;

    public CustomersService(CustomersRepository customersRepository, CustomerDTOMapper customerDTOMapper) {
        this.customersRepository = customersRepository;
        this.customerDTOMapper = customerDTOMapper;
    }

    public CustomerResponse retrieveCustomer(String username) throws GeneralException {

        try {
            Optional<Customer> dbCustomer = customersRepository.findCustomerByUserUsername(username);

            if(dbCustomer.isPresent()){
                return new CustomerResponse(
                        "SUCCESS",
                        "Retrieved customer",
                        customerDTOMapper.apply(dbCustomer.get())
                );
            }
            else {

                throw new GeneralException("Requested customer does not exist", HttpStatus.NOT_FOUND);
            }
        }
        catch (GeneralException e){

            if(e.getStatus() == HttpStatus.NOT_FOUND){
                throw e;
            }
            else{
                throw new GeneralException("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }



    }

}
