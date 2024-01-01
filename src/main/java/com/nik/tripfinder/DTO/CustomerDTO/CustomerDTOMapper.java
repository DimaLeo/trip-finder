package com.nik.tripfinder.DTO.CustomerDTO;
import com.nik.tripfinder.models.Customer;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CustomerDTOMapper implements Function<Customer, CustomerDTO> {
    @Override
    public CustomerDTO apply(Customer customer) {
        return new CustomerDTO(
                customer.getUser().getUsername(),
                customer.getUser().getUserType(),
                customer.getTaxCode(),
                customer.getName(),
                customer.getSurname(),
                customer.getEmail()
        );
    }
}
