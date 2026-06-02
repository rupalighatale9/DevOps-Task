package com.usermanagement.service;

import com.usermanagement.dto.LoginRequest;
import com.usermanagement.dto.LoginResponse;
import com.usermanagement.dto.RegisterRequest;

public interface AuthService {

    String register(RegisterRequest registerRequest);

    LoginResponse login(LoginRequest loginRequest);
}
