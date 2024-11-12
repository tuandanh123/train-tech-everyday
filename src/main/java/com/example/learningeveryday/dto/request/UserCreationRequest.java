package com.example.learningeveryday.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserCreationRequest {
    @Size(min = 2, message = "INVALID_USERNAME")
    String username;
    String password;
    String firstName;
    String lastName;
    LocalDate dob;
}
