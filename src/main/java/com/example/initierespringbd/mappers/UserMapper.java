package com.example.initierespringbd.mappers;

import com.example.initierespringbd.dtos.UserCreateRequest;
import com.example.initierespringbd.dtos.UserResponse;
import com.example.initierespringbd.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserCreateRequest req){
        if(req == null) return null;

        return User.builder()
                .firstName(req.firstName())
                .lastName(req.lastName())
                .email(req.email())
                .age(req.age())
                .hireDate(req.hireDate())
                .phoneNumber(req.phoneNumber())
                .password(req.password())
                .build();
    }

    public UserResponse toDto(User user) {

        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getAge(),
                user.getHireDate(),
                user.getPhoneNumber(),
                user.getPassword()
        );
    }


}
