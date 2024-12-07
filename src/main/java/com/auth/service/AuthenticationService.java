package com.auth.service;

import org.springframework.stereotype.Service;

import com.auth.dto.LoginUserDto;
import com.auth.dto.UserRequestDto;
import com.auth.model.User;
import com.auth.repository.UserRepo;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@Slf4j
public class AuthenticationService {
private final UserRepo userRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
        UserRepo userRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(UserRequestDto input) {
    	User user = User.builder().username(input.getUsername()).password(passwordEncoder.encode(input.getPassword())).fullname(input.getFullname()).mobile(input.getMobile()).build();
        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );
        return userRepository.findByUsername(input.getUsername())
                .orElseThrow();
    }
}