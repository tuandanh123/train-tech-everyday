package com.example.learningeveryday.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    INVALID_KEY(1006, "Invalid key"),
    UNCATEGORIZED_ERROR(9999, "Uncategorized Error"),
    INVALID_USERNAME(1001, "Invalid username"),
    INVALID_PASSWORD(1002, "Invalid password"),
    USERNAME_EXISTS(1003, "Username already exists"),
    USERNAME_NOT_FOUND(1004, "Username not found"),
    ;
    int code;
    String message;
}
