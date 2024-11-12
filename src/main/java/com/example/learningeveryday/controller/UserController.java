package com.example.learningeveryday.controller;

import com.example.learningeveryday.dto.ApiResponse;
import com.example.learningeveryday.dto.request.UserCreationRequest;
import com.example.learningeveryday.dto.request.UserUpdateRequest;
import com.example.learningeveryday.dto.response.UserResponse;
import com.example.learningeveryday.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserController {
    UserService userService;

    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest userCreationRequest){
        UserResponse userResponse = userService.createUser(userCreationRequest);

        return ApiResponse.<UserResponse>builder()
                .result(userResponse)
                .build();
    }

    @GetMapping("/{id}")
    public  ApiResponse<UserResponse> getUser(@PathVariable String id){
        UserResponse userResponse = userService.getUser(id);

        return ApiResponse.<UserResponse>builder()
                .result(userResponse)
                .build();
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> getAllUsers(){
        List<UserResponse> userResponseList = userService.getAllUsers();

        return ApiResponse.<List<UserResponse>>builder()
                .result(userResponseList)
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable String id, @RequestBody @Valid UserUpdateRequest userUpdateRequest){
        UserResponse userResponse = userService.updateUser(id, userUpdateRequest);

        return ApiResponse.<UserResponse>builder()
                .result(userResponse)
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteUser(@PathVariable String id){
        userService.deleteUser(id);

        return ApiResponse.<String>builder()
                .result("User deleted")
                .build();
    }
}
