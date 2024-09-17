package com.colak.springtutorial.repositories;

import com.colak.springtutorial.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRegistrationRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
