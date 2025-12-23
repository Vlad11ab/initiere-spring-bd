package com.example.initierespringbd.service.command.impl;

import com.example.initierespringbd.dtos.UserCreateRequest;
import com.example.initierespringbd.dtos.UserResponse;
import com.example.initierespringbd.dtos.UserUpdateRequest;
import com.example.initierespringbd.exception.EmailAlreadyUsedException;
import com.example.initierespringbd.exception.UserNotFoundException;
import com.example.initierespringbd.mappers.UserMapper;
import com.example.initierespringbd.model.User;
import com.example.initierespringbd.repository.UserRepository;
import com.example.initierespringbd.service.command.UserCommandService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserCommandServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public UserResponse create(UserCreateRequest request) {
        if (userRepository.existsByEmailJPQL(request.email())) {
            throw new EmailAlreadyUsedException(request.email());
        }
        User savedUser = userRepository.save(userMapper.toEntity(request));
        return userMapper.toDto(savedUser);
    }

    @Override
    @Transactional
    public UserResponse update(Long userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        if (request.password() != null && !request.password().isBlank()) {
            user.setPassword(request.password());
        }
        if (request.age() > 0) {
            user.setAge(request.age());
        }
        if (request.email() != null && request.email().length() > 5) {
            user.setEmail(request.email());
        }
        User updatedUser = userRepository.save(user);
        return userMapper.toDto(updatedUser);
    }

    @Override
    @Transactional
    public void delete(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        userRepository.delete(user);
    }
}
