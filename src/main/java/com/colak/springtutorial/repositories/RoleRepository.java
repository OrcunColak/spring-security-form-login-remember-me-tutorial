package com.colak.springtutorial.repositories;

import com.colak.springtutorial.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface RoleRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByRole(String role);
}
