package com.LibroFlow.demo.repository;

import com.LibroFlow.demo.dtos.BookDTO;
import com.LibroFlow.demo.entities.Book;
import com.LibroFlow.demo.enums.Genre;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class BookRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    BookRepository bookRepository;

    @Test
    @Transactional
    @DisplayName("Deve encontrar um livro por seu titulo")
    void findByTitleSuccess() {
        BookDTO dto = new BookDTO( "Titulo", "Autor", Genre.AVENTURA, "Descricao", 10, true);
        Book book = createUser(dto);
        Book found = bookRepository.findByTitle(dto.getTitle());
        assertEquals(book.getId(), found.getId());
    }

    @Test
    @Transactional
    @DisplayName("Não deve encontrar um livro por seu titulo")
    void dontFindByTitle() {
        BookDTO dto = new BookDTO( "Titulo", "Autor", Genre.AVENTURA, "Descricao", 10, true);
        createUser(dto);
        Book found = bookRepository.findByTitle("Titulo errado");
        assertNull(found);
    }

    private Book createUser(BookDTO dto) {
        Book book = new Book(dto);
        entityManager.persist(book);
        return book;
    }
}