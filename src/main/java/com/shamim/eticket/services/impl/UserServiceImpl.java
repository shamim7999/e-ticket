package com.shamim.eticket.services.impl;

import com.shamim.eticket.dtos.users.UserDto;
import com.shamim.eticket.entities.User;
import com.shamim.eticket.enums.Role;
import com.shamim.eticket.repositories.UserRepository;
import com.shamim.eticket.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> getAllCustomers() {
        return userRepository.findByRole(Role.USER);
    }

    @Override
    public List<UserDto> getAllAdmins() {
        return userRepository.findByRole(Role.ADMIN);
    }
}
