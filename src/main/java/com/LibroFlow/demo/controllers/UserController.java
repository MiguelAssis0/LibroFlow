package com.LibroFlow.demo.controllers;

import com.LibroFlow.demo.dtos.UserDTO;
import com.LibroFlow.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Void> createUser(@Validated UserDTO dto) {
        userService.createUser(dto);
        URI address = URI.create("/users");
        return ResponseEntity.created(address).build();
    }

    @GetMapping
    public ResponseEntity<UserDTO> getAllUsers() {
        UserDTO user = userService.getAllUsers();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
}
