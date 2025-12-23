package com.example.initierespringbd.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@ToString
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    @NotBlank(message = "Prenume obligatoriu")
    @Size(min = 3, max = 50, message ="Intre 3-50 caractere")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Nume de Familie obligatoriu")
    @Size(min = 3, max = 50, message ="Intre 3-50 caractere")
    private String lastName;

    @Column
    @Email
    @NotBlank(message = "Email obligatoriu")
    private String email;

    @Positive(message = "Varsta > 16 ani")
    private int age;

    @NotNull(message = "Data angajarii obligatorie")
    private LocalDate hireDate;

    @Size(min = 5, max = 20, message = "Parola trebuie sa continta intre 5-20 de caractere")
    @NotBlank
    private String password;

    @NotBlank(message = "Numar de telefon obligatoriu")
    private String phoneNumber;




}
