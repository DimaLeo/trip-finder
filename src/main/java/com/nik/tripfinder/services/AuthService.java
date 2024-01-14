package com.nik.tripfinder.services;

import com.nik.tripfinder.DTO.AgencyDTO.AgencyDTOMapper;
import com.nik.tripfinder.DTO.CustomerDTO.CustomerDTOMapper;
import com.nik.tripfinder.DTO.UserDTO.UserDTOMapper;
import com.nik.tripfinder.exceptions.GeneralException;
import com.nik.tripfinder.models.Agency;
import com.nik.tripfinder.models.Customer;
import com.nik.tripfinder.models.User;
import com.nik.tripfinder.payloads.requests.AuthenticationRequest;
import com.nik.tripfinder.payloads.requests.NewAgencyRequest;
import com.nik.tripfinder.payloads.requests.NewCustomerRequest;
import com.nik.tripfinder.payloads.responses.AgencyResponse;
import com.nik.tripfinder.payloads.responses.AuthenticationResponse;
import com.nik.tripfinder.payloads.responses.CustomerResponse;
import com.nik.tripfinder.repositories.AgenciesRepository;
import com.nik.tripfinder.repositories.CustomersRepository;
import com.nik.tripfinder.repositories.UserRepository;
import com.nik.tripfinder.util.Validator;
import org.springframework.http.HttpStatus;
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
    private final UserDTOMapper userDTOMapper;

    public AuthService(AgenciesRepository agenciesRepository, CustomersRepository customersRepository,
            UserRepository userRepository, AgencyDTOMapper agencyDTOMapper, CustomerDTOMapper customerDTOMapper,
            UserDTOMapper userDTOMapper) {
        this.agenciesRepository = agenciesRepository;
        this.customersRepository = customersRepository;
        this.userRepository = userRepository;
        this.agencyDTOMapper = agencyDTOMapper;
        this.customerDTOMapper = customerDTOMapper;
        this.userDTOMapper = userDTOMapper;
        this.encoder = new BCryptPasswordEncoder();
    }

    public AgencyResponse registerAgency(NewAgencyRequest newAgencyRequest) throws GeneralException {
        try {
            Optional<User> existingUser;
            Optional<Agency> existingAgency;
            existingUser = userRepository.findUserByUsername(newAgencyRequest.getUsername());

            if (existingUser.isPresent()) {
                throw new GeneralException("User with the specified username already exists", HttpStatus.CONFLICT);
            }

            existingAgency = agenciesRepository
                    .findAgencyByBrandNameOrTaxCode(
                            newAgencyRequest.getBrandName(),
                            newAgencyRequest.getTaxCode());

            if (existingAgency.isPresent()) {
                throw new GeneralException("Agency with the specified brand name already exists", HttpStatus.CONFLICT);
            }
            return registerAgencyUser(newAgencyRequest);
        } catch (GeneralException e) {
            throw e;
        }
    }

    public CustomerResponse registerCustomer(NewCustomerRequest newCustomerRequest) throws GeneralException {

        Optional<User> existingUser;
        Optional<Customer> existingCustomer;

        try {
            existingUser = userRepository
                    .findUserByUsername(newCustomerRequest.getUsername());

            if (existingUser.isPresent()) {
                throw new GeneralException("User with the specified username already exists", HttpStatus.CONFLICT);
            }

            existingCustomer = customersRepository
                    .findCustomerByEmailOrTaxCode(
                            newCustomerRequest.getEmail(),
                            newCustomerRequest.getTaxCode());

            if (existingCustomer.isPresent()) {
                throw new GeneralException("Customer with the specified info already exists", HttpStatus.CONFLICT);
            }

        } catch (GeneralException e) {
            throw e;
        }
        return registerCustomerUser(newCustomerRequest);

    }

    public AuthenticationResponse authenticate(AuthenticationRequest body) throws GeneralException {
        String validationErrors = authRequestValidation(body);

        if (validationErrors.isEmpty()) {
            Optional<User> existingUser;

            try {
                existingUser = userRepository.findUserByUsername(body.getUsername());
                if (!existingUser.isPresent()) {
                    throw new GeneralException("User with username: " + body.getUsername() + " does not exist.",
                            HttpStatus.UNAUTHORIZED);
                }

                User dbUser = existingUser.get();

                if (encoder.matches(body.getPassword(), dbUser.getPassword())) {
                    Integer secondary_id;

                    if (dbUser.getUserType().equals("agency")) {
                        Optional<Agency> optionalAgency = agenciesRepository.findAgencyByUserId(dbUser.getId());

                        if (optionalAgency.isPresent()) {
                            secondary_id = optionalAgency.get().getId();
                        } else {
                            throw new GeneralException("No Agency or Customer found for provided username",
                                    HttpStatus.UNAUTHORIZED);
                        }
                    } else {
                        Optional<Customer> optionalCustomer = customersRepository.findCustomerByUserId(dbUser.getId());

                        if (optionalCustomer.isPresent()) {
                            secondary_id = optionalCustomer.get().getCustomerId();
                        } else {
                            throw new GeneralException("No Agency or Customer found for provided username",
                                    HttpStatus.UNAUTHORIZED);
                        }
                    }

                    return new AuthenticationResponse(
                            "SUCCESS",
                            "Authenticated",
                            userDTOMapper.apply(dbUser),
                            secondary_id);
                } else {
                    throw new GeneralException("Wrong password", HttpStatus.UNAUTHORIZED);
                }
            } catch (GeneralException e) {
                if (e.getStatus().equals(HttpStatus.UNAUTHORIZED)) {
                    throw e;
                } else
                    throw new GeneralException("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            throw new GeneralException(
                    "Validation Error.\n" +
                            "message :\n" + validationErrors,
                    HttpStatus.BAD_REQUEST);
        }
    }

    private AgencyResponse registerAgencyUser(NewAgencyRequest body) throws GeneralException {

        String validationErrors = agencyValidation(body);

        if (validationErrors.isEmpty()) {
            String passwordHash = encoder.encode(body.getPassword());

            User newUser = new User(
                    body.getUsername(),
                    passwordHash,
                    body.getUserType());

            try {
                newUser = userRepository.save(newUser);
            } catch (Exception e) {
                throw new GeneralException("Something went wrong",
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }

            Agency newAgency = new Agency(
                    newUser,
                    body.getTaxCode(),
                    body.getBrandName(),
                    body.getOwner());

            try {
                newAgency = agenciesRepository.save(newAgency);
            } catch (Exception e) {

                throw new GeneralException("Something went wrong",
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new AgencyResponse(
                    "SUCCESS",
                    "User successfully created",
                    agencyDTOMapper.apply(newAgency));
        } else {
            throw new GeneralException(
                    "Validation Error.\n" +
                            "message :\n" +
                            validationErrors,
                    HttpStatus.BAD_REQUEST);
        }

    }

    private CustomerResponse registerCustomerUser(NewCustomerRequest body) throws GeneralException {

        String validationErrors = customerValidation(body);

        if (validationErrors.isEmpty()) {
            String passwordHash = encoder.encode(body.getPassword());

            User newUser = new User(
                    body.getUsername(),
                    passwordHash,
                    body.getUserType());

            try {
                newUser = userRepository.save(newUser);
            } catch (Exception e) {
                throw new GeneralException("Something went wrong",
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }

            Customer newCustomer = new Customer(
                    newUser,
                    body.getTaxCode(),
                    body.getName(),
                    body.getSurname(),
                    body.getEmail());

            try {
                newCustomer = customersRepository.save(newCustomer);

            } catch (Exception e) {
                throw new GeneralException("Something went wrong",
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new CustomerResponse(
                    "SUCCESS",
                    "User successfully created",
                    customerDTOMapper.apply(newCustomer));
        } else {
            throw new GeneralException(
                    "Validation Error.\n" +
                            "message :\n" +
                            validationErrors,
                    HttpStatus.BAD_REQUEST);
        }

    }

    private String authRequestValidation(AuthenticationRequest body) {

        String validationResult = "";

        if (!Validator.isValidUsername(body.getUsername())) {
            validationResult = validationResult.concat("Invalid username.\n");
        }

        if (!Validator.isValidPassword(body.getPassword())) {
            validationResult = validationResult.concat("Invalid password.\n");
        }

        return validationResult;
    }

    private String agencyValidation(NewAgencyRequest body) {
        String validationResult = "";

        if (!Validator.isValidUsername(body.getUsername())) {
            validationResult = validationResult.concat("Invalid username.\n");
        }

        if (!Validator.isValidPassword(body.getPassword())) {
            validationResult = validationResult.concat("Invalid password.\n");
        }

        if (!Validator.isValidTaxCode(body.getTaxCode())) {
            validationResult = validationResult.concat("Invalid tax code.\n");
        }

        if (!Validator.isValidOwner(body.getOwner())) {
            validationResult = validationResult.concat("Invalid owner.\n");
        }

        return validationResult;
    }

    private String customerValidation(NewCustomerRequest body) {
        String validationResult = "";

        if (!Validator.isValidUsername(body.getUsername())) {
            validationResult = validationResult.concat("Invalid username.\n");
        }

        if (!Validator.isValidPassword(body.getPassword())) {
            validationResult = validationResult.concat("Invalid password.\n");
        }

        if (!Validator.isValidTaxCode(body.getTaxCode())) {
            validationResult = validationResult.concat("Invalid tax code.\n");
        }

        if (!Validator.isValidName(body.getName())) {
            validationResult = validationResult.concat("Invalid name.\n");
        }

        if (!Validator.isValidName(body.getSurname())) {
            validationResult = validationResult.concat("Invalid surname.\n");
        }

        if (!Validator.isValidEmail(body.getEmail())) {
            validationResult = validationResult.concat("Invalid email.\n");
        }

        return validationResult;
    }
}
