package com.shamim.eticket.services.interfaces;

import com.shamim.eticket.dtos.users.UserDto;
import com.shamim.eticket.entities.User;

import java.util.Optional;
import java.util.List;

public interface UserService {

    Optional<User> findByEmail(String email);

    List<UserDto> getAllCustomers();

    List<UserDto> getAllAdmins();
}