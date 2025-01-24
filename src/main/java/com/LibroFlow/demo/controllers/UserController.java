package com.LibroFlow.demo.controllers;

import com.LibroFlow.demo.dtos.RegisterDTO;
import com.LibroFlow.demo.enums.UserRole;
import com.LibroFlow.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Page<RegisterDTO>> getAllUsers(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<RegisterDTO> users = userService.getAllUsers(pageable);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{role}")
    public ResponseEntity<Page<RegisterDTO>> getByRole(@PathVariable String role, @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<RegisterDTO> users = userService.getByRole(UserRole.valueOf(role), pageable);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{username}")
    public ResponseEntity<RegisterDTO> getUserById(@PathVariable String username) {
        RegisterDTO user = userService.getUserByName(username);
        return ResponseEntity.ok(user);
    }
}
