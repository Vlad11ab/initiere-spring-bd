package com.example.initierespringbd.services.command;

import com.example.initierespringbd.dtos.UserCreateRequest;
import com.example.initierespringbd.dtos.UserResponse;
import com.example.initierespringbd.dtos.UserPatchRequest;

public interface UserCommandService {
    UserResponse create(UserCreateRequest request);
    UserResponse patch(Long userId, UserPatchRequest request);
    void delete(Long userId);


}
