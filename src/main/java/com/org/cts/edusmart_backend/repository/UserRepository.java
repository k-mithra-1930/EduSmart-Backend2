package com.org.cts.edusmart_backend.repository;

import com.org.cts.edusmart_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //Optional<User> findByEmail(String email);

    Optional<User> findByEmail(String email);
}