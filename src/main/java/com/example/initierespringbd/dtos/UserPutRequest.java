package com.example.initierespringbd.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UserPutRequest(
        @NotBlank(message = "Prenume obligatoriu")
        String firstName,
        @NotBlank(message = "Nume de Familie obligatoriu")
        String lastName,
        @Email
        @NotBlank(message = "Email obligatoriu")
        String email,
        @Positive(message = "Varsta > 16 ani")
        @NotNull
        int age,
        @NotNull(message = "Data angajarii obligatorie")
        LocalDate hireDate,
        @Size(min = 5, max = 20, message = "Parola trebuie sa continta intre 5-20 de caractere")
        @NotBlank
        String password,
        @NotBlank(message = "Numar de telefon obligatoriu")
        String phoneNumber
){}
