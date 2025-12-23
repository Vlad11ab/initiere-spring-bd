package com.example.initierespringbd.dtos;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public record UserUpdateRequest (
        int age,
        String email,
        String password

){}
