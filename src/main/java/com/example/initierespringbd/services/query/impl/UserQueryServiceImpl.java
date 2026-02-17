package com.example.initierespringbd.services.query.impl;

import com.example.initierespringbd.dtos.UserResponse;
import com.example.initierespringbd.mappers.UserMapper;
import com.example.initierespringbd.repository.UserRepository;
import com.example.initierespringbd.services.query.UserQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class UserQueryServiceImpl implements UserQueryService {

    UserRepository userRepository;
    UserMapper userMapper;

    public UserQueryServiceImpl(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserResponse> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
//          .map(el->userMapper.toDto(el))
    }

    @Override
    public Optional<UserResponse> findByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .map(userMapper::toDto);
    }

    @Override
    public Optional<UserResponse> findByLastName(String lastName) {
        return userRepository.findByLastNameIgnoreCaseJPQL(lastName)
                .map(userMapper::toDto);
    }

    @Override
    public Optional<UserResponse> findByEmailIgnoreCase(String email) {
        return userRepository.findByEmailIgnoreCaseJPQL(email)
                .map(userMapper::toDto);
    }

    @Override
    public List<UserResponse> findByAgeRange(int minAge, int maxAge) {
        return userRepository.findByAgeRange(minAge,maxAge)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public List<UserResponse> findHiredBetween(LocalDate from, LocalDate to) {
        return userRepository.findHiredBetween(from,to)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public Page<UserResponse> search(String query, Pageable pageable) {
        return userRepository.search(query,pageable)
                .map(userMapper::toDto);
    }

    @Override
    public long countHiredBefore(LocalDate date) {
        return userRepository.countHiredBefore(date);
    }

    @Override
    public boolean userExistsByEmail(String email) {
        return userRepository.existsByEmailJPQL(email);
    }
}
