package com.example.initierespringbd.controller;


import com.example.initierespringbd.dtos.UserCreateRequest;
import com.example.initierespringbd.dtos.UserPatchRequest;
import com.example.initierespringbd.dtos.UserPutRequest;
import com.example.initierespringbd.dtos.UserResponse;
import com.example.initierespringbd.services.command.UserCommandService;
import com.example.initierespringbd.services.query.UserQueryService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users2")
@Slf4j
public class UserController {

    private UserQueryService userQueryService;
    private UserCommandService userCommandService;

    public UserController(
            UserQueryService userQueryService,
            UserCommandService userCommandService
    ){

        this.userQueryService = userQueryService;
        this.userCommandService = userCommandService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        log.info("HTTP METHOD GET ALL USERS");
        return ResponseEntity.status(HttpStatus.OK).body(userQueryService.findAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserCreateRequest user){
        log.info("HTTP POST /api/v1/users2 firstName={} lastName={} email ={} age={}", user.firstName(), user.lastName(), user.email(), user.age());
        return ResponseEntity.status(HttpStatus.CREATED).body(userCommandService.create(user));
    }

    @PatchMapping("/edit/{userId}")
    public ResponseEntity<UserResponse> patchUser(@PathVariable Long userId, @Valid @RequestBody UserPatchRequest patched){
        log.info("HTTP PATCH /api/v1/users2/{}", userId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userCommandService.patch(userId,patched));
    } //NU MERGE, updateaza doar parola

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long userId, @Valid @RequestBody UserPutRequest updated){
        log.info("HTTP PUT /api/v1/users2/{}", userId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userCommandService.update(userId,updated));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId){
        log.info("HTTP DELETE /api/v1/users2/{}", userId);
        userCommandService.delete(userId);
        return ResponseEntity.noContent().build();
    }
}


