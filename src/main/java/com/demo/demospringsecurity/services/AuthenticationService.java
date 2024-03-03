package com.demo.demospringsecurity.services;

import com.demo.demospringsecurity.dto.request.LoginRequest;
import com.demo.demospringsecurity.dto.request.RegisterRequest;
import com.demo.demospringsecurity.dto.response.AuthenticationResponse;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse login(LoginRequest request);
}
