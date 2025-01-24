package com.LibroFlow.demo.service;

import com.LibroFlow.demo.dtos.AdminDTO;
import com.LibroFlow.demo.entities.Admin;
import com.LibroFlow.demo.infra.exceptions.EventNotFoundException;
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
        Admin admin = adminRepository.findByUsername(dto.getUsername());
        if(admin == null) throw new EventNotFoundException("Esse administrador não existe");
    }
}
