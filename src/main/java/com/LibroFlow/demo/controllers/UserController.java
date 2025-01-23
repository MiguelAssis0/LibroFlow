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
    public ResponseEntity<Void> createUser(@Validated @RequestBody UserDTO dto) {
        userService.createUser(dto);
        URI address = URI.create("/users");
        return ResponseEntity.created(address).build();
    }

    @GetMapping
    public ResponseEntity<UserDTO> getAllUsers() {
        UserDTO user = userService.getAllUsers();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String username) {
        UserDTO user = userService.getUserByName(username);
        return ResponseEntity.ok(user);
    }
}
