package com.LibroFlow.demo.service;

import com.LibroFlow.demo.dtos.UserDTO;
import com.LibroFlow.demo.entities.User;
import com.LibroFlow.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(UserDTO dto){
        User user = new User(dto.getUsername(), dto.getEmail(), dto.getPassword());
        userRepository.save(user);
    }

    public UserDTO getAllUsers(){
        User user = userRepository.findAll().get(0);
        return new UserDTO(user.getUsername(), user.getEmail(), user.getPassword());
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return new UserDTO(user.getUsername(), user.getEmail(), user.getPassword());
    }
}
