package com.LibroFlow.demo.controllers;

import com.LibroFlow.demo.dtos.UserDTO;
import com.LibroFlow.demo.enums.UserRole;
import com.LibroFlow.demo.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "bearer-key")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<UserDTO>> getByRole(@PathVariable String role) {
        List<UserDTO> users = userService.getByRole(UserRole.valueOf(role));
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String username) {
        UserDTO user = userService.getUserByName(username);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO user = userService.createUser(userDTO);
        URI address = URI.create("/users/" + user.getEmail());
        return ResponseEntity.created(address).body(user);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Void> removeUser(@PathVariable Long id) {
        userService.removeUser(id);
        return ResponseEntity.noContent().build();
    }
}
