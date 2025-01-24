package com.LibroFlow.demo.controllers;

import com.LibroFlow.demo.dtos.AuthenticationDTO;
import com.LibroFlow.demo.dtos.AuthenticationResponseDTO;
import com.LibroFlow.demo.dtos.RegisterDTO;
import com.LibroFlow.demo.entities.User;
import com.LibroFlow.demo.enums.UserRole;
import com.LibroFlow.demo.infra.exceptions.EventBadRequest;
import com.LibroFlow.demo.infra.security.TokenService;
import com.LibroFlow.demo.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody @Valid AuthenticationDTO dto) {
        System.out.println(dto.username());
        System.out.println(dto.password());
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.username(),dto.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new AuthenticationResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationDTO> register(@RequestBody @Valid RegisterDTO dto) {
        if(userRepository.findByEmail(dto.getEmail()) != null) throw new EventBadRequest("Email ja cadastrado");
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.getPassword());
        User newUser = new User(dto.getUsername(), dto.getEmail(), encryptedPassword, dto.getRole());
        this.userRepository.save(newUser);
        URI address = URI.create("/auth/login");
        return ResponseEntity.created(address).build();
    }
}
