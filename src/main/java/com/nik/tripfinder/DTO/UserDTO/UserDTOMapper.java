package com.nik.tripfinder.DTO.UserDTO;
import com.nik.tripfinder.models.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDTOMapper implements Function<User, UserDTO> {
    @Override
    public UserDTO apply(User user) {

        return new UserDTO(
            user.getUsername(),
            user.getUserType()
        );
    }
}
