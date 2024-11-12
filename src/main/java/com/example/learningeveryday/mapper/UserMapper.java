package com.example.learningeveryday.mapper;

import com.example.learningeveryday.dto.request.UserCreationRequest;
import com.example.learningeveryday.dto.request.UserUpdateRequest;
import com.example.learningeveryday.dto.response.UserResponse;
import com.example.learningeveryday.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest userCreationRequest);

    UserResponse toUserResponse(User user);

    void updaterUser(@MappingTarget User user, UserUpdateRequest userUpdateRequest);
}
