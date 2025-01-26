package com.LibroFlow.demo.repository;

import com.LibroFlow.demo.dtos.UserDTO;
import com.LibroFlow.demo.entities.User;
import com.LibroFlow.demo.enums.UserRole;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;


    @Test
    @Transactional
    @DisplayName("Deve encontrar um usuário por seu username")
    void findByUsernameSuccess() {
        UserDTO user = new UserDTO("username", "password", "email", UserRole.USER);
        User createdUser = createUser(user);
        User foundUser = userRepository.findByUsername(user.getUsername());
        assertEquals(createdUser.getId(), foundUser.getId());
    }

    @Test
    @Transactional
    @DisplayName("Deve retornar null para usuário inexistente")
    void findByUsernameFail() {
        UserDTO user = new UserDTO("username", "password", "email", UserRole.USER);
        User createdUser = createUser(user);
        User foundUser = userRepository.findByUsername("username errado");
        assertNull(foundUser);
    }

    @Test
    @Transactional
    @DisplayName("Deve encontrar um usuário pelo seu cargo")
    void findByRoleSuccess() {
        UserDTO user = new UserDTO("username", "password", "email", UserRole.USER);
        User createdUser = createUser(user);
        List<User> foundUsers = userRepository.findByRole(UserRole.valueOf("USER"));
        assertFalse(foundUsers.isEmpty());
    }

    @Test
    @Transactional
    @DisplayName("Deve retornar uma lista vazia para um cargo inexistente")
    void shouldReturnIllegalArgumentExceptionWhenRoleDoesNotExist() {
        UserDTO user = new UserDTO("username", "password", "email", UserRole.USER);
        User createdUser = createUser(user);
        assertThrows(IllegalArgumentException.class, () -> {
            userRepository.findByRole(UserRole.valueOf("INVALID_ROLE"));
        });
    }


    private User createUser(UserDTO data) {
        User user = new User(data);
        entityManager.persist(user);
        return user;
    }

}