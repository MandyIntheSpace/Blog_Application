package com.application.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.application.blog.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
