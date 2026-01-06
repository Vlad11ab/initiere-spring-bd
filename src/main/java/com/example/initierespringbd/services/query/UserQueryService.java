package com.example.initierespringbd.services.query;

import com.example.initierespringbd.dtos.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<UserResponse> findAllUsers();
    Optional<UserResponse> findByEmail(String email);
    Optional<UserResponse> findByLastName(String lastName);
    Optional<UserResponse> findByEmailIgnoreCase(String email);
    List<UserResponse> findByAgeRange(int minAge, int maxAge);
    List<UserResponse> findHiredBetween(LocalDate from, LocalDate to);
    Page<UserResponse> search(String query, Pageable pageable);
    long countHiredBefore(LocalDate date);
    boolean userExistsByEmail(String email);
}
