package com.example.initierespringbd.services.command.impl;

import com.example.initierespringbd.dtos.UserCreateRequest;
import com.example.initierespringbd.dtos.UserResponse;
import com.example.initierespringbd.dtos.UserUpdateRequest;
import com.example.initierespringbd.exceptions.EmailAlreadyExistsException;
import com.example.initierespringbd.exceptions.UserNotFoundException;
import com.example.initierespringbd.mappers.UserMapper;
import com.example.initierespringbd.model.User;
import com.example.initierespringbd.repository.UserRepository;
import com.example.initierespringbd.services.command.UserCommandService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserCommandServiceImpl implements UserCommandService {

    UserRepository userRepository;
    UserMapper userMapper;

    public UserCommandServiceImpl(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public UserResponse create(UserCreateRequest request) {

        if(userRepository.existsByEmailJPQL(request.email())){
            throw new  EmailAlreadyExistsException(request.email());
        }
        User savedUser = userRepository.save(userMapper.toEntity(request));
        return userMapper.toDto(savedUser);
    }

    @Override
    @Transactional
    public UserResponse update(Long userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException(userId));

        if(request.password() != null && !request.password().isBlank()){
            user.setPassword(request.password());
        }
        if(request.age()>0){
            user.setAge(request.age());
        }
        if(request.email() != null && !request.email().isBlank() && request.email().length()>5){
            user.setEmail(request.email());
        }
        User updatedUser = userRepository.save(user);
        return userMapper.toDto(updatedUser);

    }

    @Override
    public void delete(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        userRepository.delete(user);
    }
}
