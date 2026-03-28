package com.shamim.eticket.dtos.users;

public interface UserDto {
    String getName();
    String getEmail();
    String getRole(); // returns ADMIN or CUSTOMER as String
}
