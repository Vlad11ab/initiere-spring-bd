package com.example.initierespringbd.services.command;

import com.example.initierespringbd.dtos.UserCreateRequest;
import com.example.initierespringbd.dtos.UserResponse;
import com.example.initierespringbd.dtos.UserUpdateRequest;

import java.util.Optional;

public interface UserCommandService {
    UserResponse create(UserCreateRequest request);
    UserResponse update(Long userId, UserUpdateRequest request);
    void delete(Long userId);


}
