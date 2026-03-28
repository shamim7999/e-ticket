package com.shamim.eticket.services.impl;

import com.shamim.eticket.entities.User;
import com.shamim.eticket.enums.Role;
import com.shamim.eticket.models.UserRegistration;
import com.shamim.eticket.repositories.UserRepository;
import com.shamim.eticket.services.interfaces.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    @Override
    public User register(UserRegistration userRegistration) {
        User user = User.builder()
                .name(userRegistration.getName())
                .email(userRegistration.getEmail())
                .password(userRegistration.getPassword()) // ENCODE PASSWORD
                .role(userRegistration.getRole() != null ? userRegistration.getRole() : Role.USER) // DEFAULT CUSTOMER
                .build();

        return userRepository.save(user); // SAVE ENTITY
    }
}