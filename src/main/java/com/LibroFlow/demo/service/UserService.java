package com.LibroFlow.demo.service;

import com.LibroFlow.demo.dtos.RegisterDTO;
import com.LibroFlow.demo.entities.User;
import com.LibroFlow.demo.enums.UserRole;
import com.LibroFlow.demo.infra.exceptions.EventNotFoundException;
import com.LibroFlow.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;



    public Page<RegisterDTO> getAllUsers(Pageable pageable){
        Page<User> users = userRepository.findAll(pageable);
        if(users.isEmpty()) throw new EventNotFoundException("Usuários não encontrados");
        return users.map(user -> new RegisterDTO(
                user.getUsername(),
                user.getEmail(),
                null,
                user.getRole()
        ));
    }

    public Page<RegisterDTO> getByRole(UserRole role, Pageable pageable){
        Page<User> users = userRepository.findByRole(role, pageable);
        return users.map(user -> new RegisterDTO(
                user.getUsername(),
                user.getEmail(),
                null,
                user.getRole()
        ));
    }

    public RegisterDTO getUserByName(String username){
        User user = userRepository.findByUsername(username);
        return new RegisterDTO(user.getUsername(), user.getEmail(), user.getPassword());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
