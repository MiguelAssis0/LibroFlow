package com.LibroFlow.demo.repository;

import com.LibroFlow.demo.entities.User;
import com.LibroFlow.demo.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    List<User> findByRole(UserRole role);

    User findByEmail(String email);
}
