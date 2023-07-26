package com.application.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.application.blog.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
