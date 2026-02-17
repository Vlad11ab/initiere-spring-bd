package com.example.initierespringbd.dtos;

public record UserPatchRequest(
        int age,
        String email,
        String password
){}
