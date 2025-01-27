package com.LibroFlow.demo.repository;

import com.LibroFlow.demo.dtos.BookDTO;
import com.LibroFlow.demo.dtos.BorrowBookDTO;
import com.LibroFlow.demo.dtos.UserDTO;
import com.LibroFlow.demo.entities.Book;
import com.LibroFlow.demo.entities.BorrowBook;
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
class BorrowBookRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    BorrowBookRepository borrowBookRepository;

    private User testUser;
    private Book testBook;

    @BeforeEach
    void setUp() {
        UserDTO user = new UserDTO("username", "password", "email", UserRole.USER);
        testUser = createUser(user);
        BookDTO book = new BookDTO("title", "author", Genre.AVENTURA, "description", 10, true);
        testBook = createBook(book);
    }

    @Test
    @DisplayName("Deve encontrar empréstimo existente por usuário e livro")
    void findExistingBorrowBookSuccess() {
        BorrowBookDTO borrowBookDTO = new BorrowBookDTO(1L, 1L, LocalDate.now().plusDays(10));
        BorrowBook borrowBook = createBorrowBook(borrowBookDTO, testUser, testBook);

        BorrowBook found = borrowBookRepository.findByUserAndBook(testUser.getId(), testBook.getId());

        assertNotNull(found);
        assertEquals(borrowBook.getId(), found.getId());
        assertEquals(borrowBook.getBorrowDate(), found.getBorrowDate());
        assertEquals(borrowBook.getReturnDate(), found.getReturnDate());
        assertEquals(borrowBook.getIsReturned(), found.getIsReturned());
    }

    @Test
    @Transactional
    @DisplayName("Deve retornar null para empréstimo inexistente")
    void findNonExistentBorrowBook() {
        BorrowBook found = borrowBookRepository.findByUserAndBook(999L, 999L);

        assertNull(found);
    }

    private User createUser(UserDTO data) {
        User user = new User(data);
        entityManager.persist(user);
        return user;
    }

    private Book createBook(BookDTO data) {
        Book book = new Book(data);
        entityManager.persist(book);
        return book;
    }

    private BorrowBook createBorrowBook(BorrowBookDTO data, User user, Book book) {
        BorrowBook borrowBook = new BorrowBook(data, user, book);
        entityManager.persist(borrowBook);
        entityManager.flush();
        return borrowBook;
    }
}