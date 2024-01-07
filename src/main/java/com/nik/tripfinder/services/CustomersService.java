package com.nik.tripfinder.services;

import com.nik.tripfinder.DTO.CustomerDTO.CustomerDTO;
import com.nik.tripfinder.DTO.CustomerDTO.CustomerDTOMapper;
import com.nik.tripfinder.models.Customer;
import com.nik.tripfinder.payloads.responses.CustomerResponse;
import com.nik.tripfinder.repositories.CustomersRepository;
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

    public CustomerResponse retrieveCustomer(String username){

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
                return new CustomerResponse(
                        "FAILED",
                        "Customer does not exist in db"
                );
            }
        }
        catch (Exception e){
            return new CustomerResponse(
                    "FAILED",
                    "Could not retrieve customer from db.\n"+
                            "message: "+e.getMessage()
            );
        }



    }

}
