package com.LibroFlow.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;

    public Admin() {}

    public Admin(String username, String email, String password) {
        this.username = username.toLowerCase();
        this.email = email.toLowerCase();
        this.password = password;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return this.username.substring(0, 1).toUpperCase() + this.username.substring(1); }
    public void setUsername(String username) { this.username = username.toLowerCase(); }
    public String getEmail() { return email.toLowerCase(); }
    public void setEmail(String email) { this.email = email.toLowerCase(); }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
