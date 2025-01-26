package com.LibroFlow.demo.repository;

import com.LibroFlow.demo.dtos.BooksDTO;
import com.LibroFlow.demo.dtos.BorrowBooksDTO;
import com.LibroFlow.demo.dtos.UserDTO;
import com.LibroFlow.demo.entities.Books;
import com.LibroFlow.demo.entities.BorrowBooks;
import com.LibroFlow.demo.entities.User;
import com.LibroFlow.demo.enums.Genre;
import com.LibroFlow.demo.enums.UserRole;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class BorrowBooksRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    BorrowBooksRepository borrowBooksRepository;

    private User testUser;
    private Books testBook;

    @BeforeEach
    void setUp() {
        UserDTO user = new UserDTO("username", "password", "email", UserRole.USER);
        testUser = createUser(user);
        BooksDTO book = new BooksDTO("title", "author", Genre.AVENTURA, "description", 10, true);
        testBook = createBook(book);
    }

    @Test
    @Transactional
    @DisplayName("Deve encontrar empréstimo existente por usuário e livro")
    void findExistingBorrowBookSuccess() {
        BorrowBooksDTO borrowBook = new BorrowBooksDTO(testUser.getId(), testBook.getId(), LocalDate.of(2025, 2, 8), false);
        BorrowBooks borrowBookCreated = createBorrowBook(borrowBook, testUser, testBook);

        BorrowBooks found = borrowBooksRepository.findByUserAndBook(testUser.getId(), testBook.getId());

        assertNotNull(found);
        assertEquals(borrowBookCreated.getId(), found.getId());
        assertEquals(borrowBookCreated.getBorrowDate(), found.getBorrowDate());
        assertEquals(borrowBookCreated.getReturnDate(), found.getReturnDate());
        assertEquals(borrowBookCreated.getIsReturned(), found.getIsReturned());
    }

    @Test
    @Transactional
    @DisplayName("Deve retornar null para empréstimo inexistente")
    void findNonExistentBorrowBook() {
        BorrowBooks found = borrowBooksRepository.findByUserAndBook(999L, 999L);

        assertNull(found);
    }

    private User createUser(UserDTO data) {
        User user = new User(data);
        entityManager.persist(user);
        return user;
    }

    private Books createBook(BooksDTO data) {
        Books book = new Books(data);
        entityManager.persist(book);
        return book;
    }

    private BorrowBooks createBorrowBook(BorrowBooksDTO data, User user, Books book) {
        BorrowBooks borrowBook = new BorrowBooks(data, user, book);
        entityManager.persist(borrowBook);
        return borrowBook;
    }
}