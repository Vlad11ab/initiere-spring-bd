package com.example.initierespringbd.service.command;

import com.example.initierespringbd.dtos.UserCreateRequest;
import com.example.initierespringbd.dtos.UserResponse;
import com.example.initierespringbd.dtos.UserUpdateRequest;

public interface UserCommandService {
    UserResponse create(UserCreateRequest request);
    UserResponse update(Long userId, UserUpdateRequest request);
    void delete(Long userId);
}
