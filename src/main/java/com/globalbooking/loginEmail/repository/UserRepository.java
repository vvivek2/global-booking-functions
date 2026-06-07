package com.globalbooking.loginEmail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmailId(String emailId);
    Optional<User> findByEmailId(String emailId);
    Optional<User> findByMobileNumber(String mobileNumber);
}
