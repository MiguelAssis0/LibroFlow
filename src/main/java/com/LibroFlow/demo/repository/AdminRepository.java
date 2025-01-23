package com.LibroFlow.demo.repository;

import com.LibroFlow.demo.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Iterable<Admin> findByUsername(String username);
}
