package com.example.learningeveryday.service;

import com.example.learningeveryday.dto.request.UserCreationRequest;
import com.example.learningeveryday.dto.request.UserUpdateRequest;
import com.example.learningeveryday.dto.response.UserResponse;
import com.example.learningeveryday.entity.User;
import com.example.learningeveryday.exception.AppException;
import com.example.learningeveryday.exception.ErrorCode;
import com.example.learningeveryday.mapper.UserMapper;
import com.example.learningeveryday.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    public UserResponse createUser(UserCreationRequest userCreationRequest){
        String username = userCreationRequest.getUsername();

        if(userRepository.existsByUsername(username))
            throw new AppException(ErrorCode.USERNAME_EXISTS);

        User user = userMapper.toUser(userCreationRequest);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user = userRepository.save(user);

        return userMapper.toUserResponse(user);
    }

    public UserResponse getUser(String id){
        User user = userRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.USERNAME_NOT_FOUND)
        );

        return userMapper.toUserResponse(user);
    }

    public List<UserResponse> getAllUsers(){
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse).toList();
    }

    public UserResponse updateUser(String id, UserUpdateRequest userUpdateRequest){
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("dd")
        );

        userMapper.updaterUser(user, userUpdateRequest);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String id){userRepository.deleteById(id);}
}
