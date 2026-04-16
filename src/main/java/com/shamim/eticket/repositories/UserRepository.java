package com.shamim.eticket.repositories;

import com.shamim.eticket.dtos.users.UserDto;
import com.shamim.eticket.entities.User;
import com.shamim.eticket.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    // Projection example: return only necessary fields
    List<UserDto> findByRole(Role role);

    // Optional: single user projection
    @Query("SELECT u.name as name, u.email as email, u.role as role FROM User u WHERE u.email = ?1")
    Optional<UserDto> getUserProjectionByEmail(String email);
}
