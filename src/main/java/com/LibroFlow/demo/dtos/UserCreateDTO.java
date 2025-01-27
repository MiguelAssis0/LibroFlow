package com.LibroFlow.demo.dtos;

import com.LibroFlow.demo.entities.User;
import com.LibroFlow.demo.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserCreateDTO {
    @NotBlank
    private String username;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    private UserRole role;

    public UserCreateDTO() {}

    public UserCreateDTO(String username, String email, String password, UserRole role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserCreateDTO(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
