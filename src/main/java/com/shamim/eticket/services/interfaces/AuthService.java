package com.shamim.eticket.services.interfaces;

import com.shamim.eticket.entities.User;
import com.shamim.eticket.models.UserRegistration;

public interface AuthService {

    User register(UserRegistration userRegistration);

}