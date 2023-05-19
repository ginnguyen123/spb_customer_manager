package com.service.user;

import com.model.User;
import com.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    User getByUsername(String username);

    Optional<User> findByUsername(String username);

//    Optional<UserDTO> findUserDTOByUsername(String username);

    Boolean existsByUsername(String email);
}
