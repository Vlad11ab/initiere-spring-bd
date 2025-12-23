package com.example.initierespringbd.dtos;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public record UserResponse (
        Long id,
        String firstName,
        String lastName,
        String email,
        int age,
        LocalDate hireDate,
        String phoneNumber,
        String password
)
{}
