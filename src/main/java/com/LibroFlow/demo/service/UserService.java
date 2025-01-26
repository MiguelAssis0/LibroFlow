package com.LibroFlow.demo.service;

import com.LibroFlow.demo.dtos.UserDTO;
import com.LibroFlow.demo.entities.User;
import com.LibroFlow.demo.enums.UserRole;
import com.LibroFlow.demo.infra.exceptions.EventIllegalArgumentException;
import com.LibroFlow.demo.infra.exceptions.EventNotFoundException;
import com.LibroFlow.demo.infra.security.EncryptPassword;
import com.LibroFlow.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAllUsers(){
        List<User> users = userRepository.findAll();
        if(users.isEmpty()) throw new EventNotFoundException("Usuários não encontrados");
        return users.stream().map(UserDTO::new).toList();
    }

    public List<UserDTO> getByRole(UserRole role){
        List<User> users = userRepository.findByRole(role);
        return users.stream().map(UserDTO::new).toList();
    }

    public UserDTO getUserByName(String username){
        User user = userRepository.findByUsername(username);
        return new UserDTO(user.getUsername(), user.getEmail(), user.getPassword());
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public UserDTO createUser(UserDTO data) {
        data.setPassword(EncryptPassword.encryptPassword(data.getPassword()));
        User user = new User(data);

        if(userRepository.findByEmail(user.getEmail()) != null) throw new EventIllegalArgumentException("Email ja cadastrado");
        if(userRepository.findByUsername(user.getUsername()) != null) throw new EventIllegalArgumentException("Username ja cadastrado");
        userRepository.save(user);
        return new UserDTO(user);
    }

    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }
}
