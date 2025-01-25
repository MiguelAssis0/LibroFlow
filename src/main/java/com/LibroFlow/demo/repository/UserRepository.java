package com.LibroFlow.demo.repository;

import com.LibroFlow.demo.entities.User;
import com.LibroFlow.demo.enums.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByEmail(String username);

    User findByUsername(String username);

    List<User> findByRole(UserRole role);
}
