package com.LibroFlow.demo.controllers;

import com.LibroFlow.demo.dtos.AdminDTO;
import com.LibroFlow.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping
    public ResponseEntity<Void> createAdmin(@RequestBody AdminDTO dto) {
        adminService.createAdmin(dto);
        URI address = URI.create("/admin/" + dto.getId());
        return ResponseEntity.created(address).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody AdminDTO dto) {
        adminService.login(dto);
        return ResponseEntity.ok().build();
    }
}
