package com.example.initierespringbd.services;

import com.example.initierespringbd.dtos.UserCreateRequest;
import com.example.initierespringbd.dtos.UserResponse;
import com.example.initierespringbd.model.User;

import java.util.Optional;

public interface UserCommandService {
    UserResponse create(UserCreateRequest request);


}
