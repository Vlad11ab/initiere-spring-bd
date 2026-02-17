package com.example.initierespringbd.repository;

import com.example.initierespringbd.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {

    Optional<User>  findUserByEmail(String email);

    @Query("select u from User u where lower(u.lastName) = lower(:lastName)")
    Optional<User> findByLastNameIgnoreCaseJPQL(@Param("lastName") String lastName);

    @Query("select u from User u where lower(u.email) = lower(:email)")
    Optional<User> findByEmailIgnoreCaseJPQL(@Param("email") String email);

    @Query("select u from User u \n" +
            "where u.age between :minAge and :maxAge\n" +
            "order by u.age asc"
           )
    List<User> findByAgeRange(@Param("minAge") int minAge,
                               @Param("maxAge") int maxAge);

    @Query("select u from User u \n" +
           "where u.hireDate >= :from and u.hireDate <= :to\n" +
           "order by u.hireDate asc"
    )
    List<User> findHiredBetween(@Param("from") LocalDate from,
                                      @Param("to") LocalDate to);

    @Query("select u from User u " +
           "where lower(u.firstName) like lower(concat('%', :q, '%'))" +
           "or lower(u.email) like lower(concat('%', :q, '%'))" +
           "or u.phoneNumber like concat('%', :q, '%')"
    )
    Page<User> search (@Param("q") String q, Pageable pageable);

    @Query("select count(u) from User u where u.hireDate <= :date")
    long countHiredBefore(@Param("date") LocalDate date);

    @Query("select (count(u) > 0) from User u where lower(u.email) = lower(:email)")
    boolean existsByEmailJPQL(@Param("email") String email);



}
