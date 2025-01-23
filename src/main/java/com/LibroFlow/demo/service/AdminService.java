package com.LibroFlow.demo.service;

import com.LibroFlow.demo.dtos.AdminDTO;
import com.LibroFlow.demo.entities.Admin;
import com.LibroFlow.demo.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public void createAdmin(AdminDTO dto){
        Admin admin = new Admin(dto.getUsername(), dto.getEmail(), dto.getPassword());
        adminRepository.save(admin);
    }

    public void login(AdminDTO dto){
        Admin admin = (Admin) adminRepository.findByUsername(dto.getUsername());
        if(admin.getEmail().equals(dto.getEmail()) && admin.getPassword().equals(dto.getPassword())){
            System.out.println("Login successful");
        }
        else{
            System.out.println("Login failed");
        }
    }
}
