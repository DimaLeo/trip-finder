package com.nik.tripfinder.services;

import com.nik.tripfinder.DTO.AgencyDTO.AgencyDTOMapper;
import com.nik.tripfinder.DTO.CustomerDTO.CustomerDTOMapper;
import com.nik.tripfinder.models.Agency;
import com.nik.tripfinder.models.Customer;
import com.nik.tripfinder.models.User;
import com.nik.tripfinder.payloads.requests.AuthenticationRequest;
import com.nik.tripfinder.payloads.requests.NewAgencyRequest;
import com.nik.tripfinder.payloads.requests.NewCustomerRequest;
import com.nik.tripfinder.payloads.responses.AgencyResponse;
import com.nik.tripfinder.payloads.responses.CustomerResponse;
import com.nik.tripfinder.repositories.AgenciesRepository;
import com.nik.tripfinder.repositories.CustomersRepository;
import com.nik.tripfinder.repositories.UserRepository;
import com.nik.tripfinder.util.Validator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final AgenciesRepository agenciesRepository;
    private final CustomersRepository customersRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final AgencyDTOMapper agencyDTOMapper;
    private final CustomerDTOMapper customerDTOMapper;


    public AuthService(AgenciesRepository agenciesRepository, CustomersRepository customersRepository, UserRepository userRepository, AgencyDTOMapper agencyDTOMapper, CustomerDTOMapper customerDTOMapper){
        this.agenciesRepository = agenciesRepository;
        this.customersRepository = customersRepository;
        this.userRepository = userRepository;
        this.agencyDTOMapper = agencyDTOMapper;
        this.customerDTOMapper = customerDTOMapper;
        this.encoder = new BCryptPasswordEncoder();
    }

    public AgencyResponse registerAgency(NewAgencyRequest newAgencyRequest) {
        Optional<User> existingUser;
        Optional<Agency> existingAgency;

        try{
            existingUser = userRepository
                    .findUserByUsername(newAgencyRequest.getUsername());

            if(existingUser.isPresent()){
                return new AgencyResponse(
                        "FAILED",
                        "User already exists"
                );
            }

        }
        catch (Exception e){
            return new AgencyResponse(
                    "FAILED",
                    "Failed to retrieve user from db.\n" +
                            "message : "+ e.getMessage()
            );

        }

        try{
            existingAgency = agenciesRepository
                    .findAgencyByBrandNameOrTaxCode(
                            newAgencyRequest.getBrandName(),
                            newAgencyRequest.getTaxCode());

            if(existingAgency.isPresent()){
                return new AgencyResponse(
                        "FAILED",
                        "Agency already exists"
                );
            }

        }
        catch (Exception e){
            return new AgencyResponse(
                    "FAILED",
                    "Failed to retrieve agency from db.\n" +
                            "message : "+ e.getMessage()
            );
        }

        return registerAgencyUser(newAgencyRequest);


    }


    public CustomerResponse registerCustomer(NewCustomerRequest newCustomerRequest) {

        Optional<User> existingUser;
        Optional<Customer> existingCustomer;

        try{
            existingUser = userRepository
                    .findUserByUsername(newCustomerRequest.getUsername());

            if(existingUser.isPresent()){
                return new CustomerResponse(
                        "FAILED",
                        "User already exists"
                );
            }

        }
        catch (Exception e){
            return new CustomerResponse(
                    "FAILED",
                    "Failed to retrieve user from db.\n" +
                            "message : "+ e.getMessage()
            );

        }

        try{
            existingCustomer = customersRepository
                    .findCustomerByEmailOrTaxCode(
                            newCustomerRequest.getEmail(),
                            newCustomerRequest.getTaxCode());

            if(existingCustomer.isPresent()){
                return new CustomerResponse(
                        "FAILED",
                        "Customer already exists"
                );
            }

        }
        catch (Exception e){
            return new CustomerResponse(
                    "FAILED",
                    "Failed to retrieve agency from db.\n" +
                            "message : "+ e.getMessage()
            );
        }

        return registerCustomerUser(newCustomerRequest);

    }

    public AgencyResponse authenticateAgency(AuthenticationRequest body) {

        String validationErrors = authRequestValidation(body);

       if(validationErrors.isEmpty()){

           Optional<User> existingUser;

           try{
               existingUser = userRepository.findUserByUsername(body.getUsername());

               if(!existingUser.isPresent()){
                   return new AgencyResponse(
                           "FAILED",
                           "User with username: "+ body.getUsername() + " does not exist."
                   );
               }

               User dbUser = existingUser.get();

               if(encoder.matches(body.getPassword(), dbUser.getPassword())){

                   Optional<Agency> existingAgency;

                   try{
                       existingAgency = agenciesRepository.findAgencyById(dbUser.getId());
                       if(!existingAgency.isPresent()){
                           return new AgencyResponse(
                                   "FAILED",
                                   "Agency not found, something went wrong with the registration"
                           );
                       }

                       Agency dbAgency = existingAgency.get();

                       return new AgencyResponse(
                               "SUCCESS",
                               "Authenticated",
                               agencyDTOMapper.apply(dbAgency)
                       );

                   }
                   catch (Exception e){
                       return new AgencyResponse(
                               "FAILED",
                               "Failed to retrieve agency from db.\n" +
                                       "message : "+ e.getMessage()
                       );
                   }



               }
               else{
                   return new AgencyResponse(
                           "FAILED",
                           "Wrong password."
                   );
               }


           }
        catch (Exception e){
               return new AgencyResponse(
                       "FAILED",
                       "Failed to retrieve user from db.\n" +
                               "message : "+ e.getMessage()
               );

           }

       }
       else {
           return new AgencyResponse(
                   "FAILED",
                   "Validation Error.\n" +
                           "message :\n"+
                           validationErrors
           );
       }

    }

    public CustomerResponse authenticateCustomer(AuthenticationRequest body) {

        String validationErrors = authRequestValidation(body);

        if(validationErrors.isEmpty()){

            Optional<User> existingUser;

            try{
                existingUser = userRepository.findUserByUsername(body.getUsername());

                if(!existingUser.isPresent()){
                    return new CustomerResponse(
                            "FAILED",
                            "User with username: "+ body.getUsername() + " does not exist."
                    );
                }

                User dbUser = existingUser.get();

                if(encoder.matches(body.getPassword(), dbUser.getPassword())){

                    Optional<Customer> existingCustomer;

                    try{
                        existingCustomer = customersRepository.findCustomerById(dbUser.getId());
                        if(!existingCustomer.isPresent()){
                            return new CustomerResponse(
                                    "FAILED",
                                    "Customer not found, something went wrong with the registration"
                            );
                        }

                        Customer dbCustomer = existingCustomer.get();

                        return new CustomerResponse(
                                "SUCCESS",
                                "Authenticated",
                                customerDTOMapper.apply(dbCustomer)
                        );

                    }
                    catch (Exception e){
                        return new CustomerResponse(
                                "FAILED",
                                "Failed to retrieve customer from db.\n" +
                                        "message : "+ e.getMessage()
                        );
                    }



                }
                else{
                    return new CustomerResponse(
                            "FAILED",
                            "Wrong password."
                    );
                }


            }
            catch (Exception e){
                return new CustomerResponse(
                        "FAILED",
                        "Failed to retrieve user from db.\n" +
                                "message : "+ e.getMessage()
                );

            }

        }
        else {
            return new CustomerResponse(
                    "FAILED",
                    "Validation Error.\n" +
                            "message :\n"+
                            validationErrors
            );
        }

    }

    private AgencyResponse registerAgencyUser(NewAgencyRequest body){

        String validationErrors = agencyValidation(body);

        if(validationErrors.isEmpty()){
            String passwordHash = encoder.encode(body.getPassword());

            User newUser = new User(
                    body.getUsername(),
                    passwordHash,
                    body.getUserType());

            try{
                userRepository.save(newUser);
            }
            catch (Exception e){
                return new AgencyResponse(
                        "FAILED",
                        "Failed to insert user to the db.\n" +
                                "message : "+ e.getMessage()
                );
            }

            try{
                newUser = userRepository.findUserByUsername(newUser.getUsername()).get();
            }
            catch (Exception e){
                return new AgencyResponse(
                        "FAILED",
                        "Failed to retrieve newly inserted user.\n" +
                                "message : "+ e.getMessage()
                );
            }

            Agency newAgency = new Agency(
                    newUser,
                    body.getTaxCode(),
                    body.getBrandName(),
                    body.getOwner());

            agenciesRepository.save(newAgency);


            return new AgencyResponse(
                    "SUCCESS",
                    "User successfully created",
                    agencyDTOMapper.apply(newAgency)
            );
        }
        else{
            return new AgencyResponse(
                    "FAILED",
                    "Validation Error.\n" +
                            "message :\n"+
                            validationErrors
            );
        }



    }
    private CustomerResponse registerCustomerUser(NewCustomerRequest body){

        String validationErrors = customerValidation(body);

        if(validationErrors.isEmpty()){
            String passwordHash = encoder.encode(body.getPassword());

            User newUser = new User(
                    body.getUsername(),
                    passwordHash,
                    body.getUserType());

            try{
                userRepository.save(newUser);
            }
            catch (Exception e){
                return new CustomerResponse(
                        "FAILED",
                        "Failed to insert user to the db.\n" +
                                "message : "+ e.getMessage()
                );
            }

            try{
                newUser = userRepository.findUserByUsername(newUser.getUsername()).get();
            }
            catch (Exception e){
                return new CustomerResponse(
                        "FAILED",
                        "Failed to retrieve newly inserted user.\n" +
                                "message : "+ e.getMessage()
                );
            }

            Customer newCustomer = new Customer(
                    newUser,
                    body.getTaxCode(),
                    body.getName(),
                    body.getSurname(),
                    body.getEmail());

            customersRepository.save(newCustomer);


            return new CustomerResponse(
                    "SUCCESS",
                    "User successfully created",
                    customerDTOMapper.apply(newCustomer)
            );
        }
        else{
            return new CustomerResponse(
                    "FAILED",
                    "Validation Error.\n" +
                            "message :\n"+
                            validationErrors
            );
        }



    }

    private String authRequestValidation(AuthenticationRequest body){

        String validationResult = "";

        if(!Validator.isValidUsername(body.getUsername())){
            validationResult = validationResult.concat("Invalid username.\n");
        }

        if(!Validator.isValidPassword(body.getPassword())){
            validationResult = validationResult.concat("Invalid password.\n");
        }

        return validationResult;
    }

    private String agencyValidation(NewAgencyRequest body){
        String validationResult = "";

        if(!Validator.isValidUsername(body.getUsername())){
            validationResult = validationResult.concat("Invalid username.\n");
        }

        if(!Validator.isValidPassword(body.getPassword())){
            validationResult = validationResult.concat("Invalid password.\n");
        }

        if(!Validator.isValidTaxCode(body.getTaxCode())){
            validationResult = validationResult.concat("Invalid tax code.\n");
        }

        if(!Validator.isValidOwner(body.getOwner())){
            validationResult = validationResult.concat("Invalid owner.\n");
        }

        return validationResult;
    }

    private String customerValidation(NewCustomerRequest body){
        String validationResult = "";

        if(!Validator.isValidUsername(body.getUsername())){
            validationResult = validationResult.concat("Invalid username.\n");
        }

        if(!Validator.isValidPassword(body.getPassword())){
            validationResult = validationResult.concat("Invalid password.\n");
        }

        if(!Validator.isValidTaxCode(body.getTaxCode())){
            validationResult = validationResult.concat("Invalid tax code.\n");
        }

        if(!Validator.isValidName(body.getName())){
            validationResult = validationResult.concat("Invalid name.\n");
        }

        if(!Validator.isValidName(body.getSurname())){
            validationResult = validationResult.concat("Invalid surname.\n");
        }

        if(!Validator.isValidEmail(body.getEmail())){
            validationResult = validationResult.concat("Invalid email.\n");
        }

        return validationResult;
    }

}
