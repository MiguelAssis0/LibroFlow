package com.LibroFlow.demo.service;

import com.LibroFlow.demo.dtos.UserCreateDTO;
import com.LibroFlow.demo.dtos.UserDTO;
import com.LibroFlow.demo.entities.User;
import com.LibroFlow.demo.enums.UserRole;
import com.LibroFlow.demo.infra.exceptions.EventIllegalArgumentException;
import com.LibroFlow.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CacheService cacheService;

    @InjectMocks
    private UserService userService;

    @Test
    @Transactional
    @DisplayName("Deve retornar uma lista de usuarios")
    void getAllUsers() {
        User user = new User(1L, "username", "email@email.com", "12345", UserRole.USER);
        User user2 = new User(1L, "username2", "email@gmail.com", "12345", UserRole.USER);
        User user3 = new User(1L, "username3", "email@hotmail.com", "12345", UserRole.USER);
        User user4 = new User(1L, "username4", "email@outlook.com", "12345", UserRole.USER);
        when(userRepository.findAll()).thenReturn(List.of(user, user2, user3, user4));
        List<UserDTO> users = userService.getAllUsers();
        assertNotNull(users);
        assertEquals(4, users.size());
    }

    @Test
    @Transactional
    @DisplayName("Deve encontrar um usuário pelo cargo")
    void getByRole() {
        User user = new User(1L, "username", "email@email.com", "12345", UserRole.USER);
        User user2 = new User(1L, "username2", "email@gmail.com", "12345", UserRole.ADMIN);
        User user3 = new User(1L, "username3", "email@hotmail.com", "12345", UserRole.USER);
        User user4 = new User(1L, "username4", "email@outlook.com", "12345", UserRole.USER);
        when(userRepository.findByRole(UserRole.USER)).thenReturn(List.of(user, user3, user4));
        List<UserDTO> users = userService.getByRole(UserRole.USER);
        assertNotNull(users);
        assertEquals(3, users.size());
    }

    @Test
    @Transactional
    @DisplayName("Deve encontrar um usuário pelo username")
    void getUserByName() {
        User user = new User(1L, "username", "email@email.com", "12345", UserRole.USER);
        User user2 = new User(1L, "username2", "email@gmail.com", "12345", UserRole.ADMIN);
        User user3 = new User(1L, "username3", "email@hotmail.com", "12345", UserRole.USER);
        User user4 = new User(1L, "username4", "email@outlook.com", "12345", UserRole.USER);
        when(userRepository.findByUsername("username")).thenReturn(user);
        UserDTO userDTO = userService.getUserByName("username");
        assertNotNull(userDTO);
        assertEquals(user.getUsername(), userDTO.getUsername());
    }

    @Test
    @Transactional
    @DisplayName("Deve criar um usuário")
    void createUser() {
        UserCreateDTO userDTO = new UserCreateDTO("usuario01", "email@gmail.com", "12345", UserRole.USER);
        UserDTO createdUserDTO = userService.createUser(userDTO);
        assertNotNull(createdUserDTO);
        assertEquals(userDTO.getUsername(), createdUserDTO.getUsername());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    @Transactional
    @DisplayName("Deve retornar um erro ao tentar criar um usuário com username duplicado")
    void createUserWithDuplicateUsername() {
        UserCreateDTO userDTO = new UserCreateDTO("username", "email@email.com", "12345", UserRole.USER);
        when(userRepository.findByUsername(userDTO.getUsername())).thenReturn(new User(1L, "username", "email@email.com", "12345", UserRole.USER));
        assertThrows(EventIllegalArgumentException.class, () -> userService.createUser(userDTO));
    }

    @Test
    @Transactional
    @DisplayName("Deve retornar um erro ao tentar criar um usuário com email duplicado")
    void createUserWithDuplicateEmail() {
        UserCreateDTO userDTO = new UserCreateDTO("username", "email@email.com", "12345", UserRole.USER);
        UserCreateDTO userDTO2 = new UserCreateDTO("username2", "email@email.com", "12345", UserRole.USER);
        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(new User(1L, "username", "email@email.com", "12345", UserRole.USER));
        assertThrows(EventIllegalArgumentException.class, () -> userService.createUser(userDTO2));
    }

    @Test
    @Transactional
    @DisplayName("Deve deletar um usuário")
    void removeUser() {
        UserCreateDTO user = new UserCreateDTO("username", "email@email.com", "12345", UserRole.USER);
        userService.createUser(user);
        userService.removeUser(1L);
        assertFalse(userRepository.existsById(1L));
    }
}