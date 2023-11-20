package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

public record RequestStudentDto(
        @NotBlank
        @NonNull
        @NotEmpty
        String name,

        @NotBlank
        @NonNull
        @NotEmpty
        @Email
        String email,
        LocalDate dateOfBirth
) { }
