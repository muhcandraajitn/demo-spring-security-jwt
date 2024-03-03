package com.demo.demospringsecurity.services.impl;

import com.demo.demospringsecurity.config.JwtService;
import com.demo.demospringsecurity.dto.request.LoginRequest;
import com.demo.demospringsecurity.dto.request.RegisterRequest;
import com.demo.demospringsecurity.dto.response.AuthenticationResponse;
import com.demo.demospringsecurity.model.Member;
import com.demo.demospringsecurity.repository.MemberRepository;
import com.demo.demospringsecurity.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        var member = Member.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .isEnabled(true)
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .pin(passwordEncoder.encode(request.getPin()))
                .role(request.getRole())
                .build();
        memberRepository.save(member);
        var jwtToken = jwtService.generateToken(member);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getPhoneNumber(),
                request.getPin()
        ));
        var member = memberRepository.findByPhoneNumber(request.getPhoneNumber()).orElseThrow(() -> new RuntimeException("User not found"));
        var jwtToken = jwtService.generateToken(member);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
