package com.example.learningeveryday.service;

import com.example.learningeveryday.dto.request.AuthenticationRequest;
import com.example.learningeveryday.dto.response.AuthenticationResponse;
import com.example.learningeveryday.entity.User;
import com.example.learningeveryday.exception.AppException;
import com.example.learningeveryday.exception.ErrorCode;
import com.example.learningeveryday.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        String username = authenticationRequest.getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new AppException(ErrorCode.USERNAME_NOT_FOUND)
        );

        boolean authenticated = passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());

        return AuthenticationResponse.builder()
                .authenticated(authenticated)
                .build();

    }
}
