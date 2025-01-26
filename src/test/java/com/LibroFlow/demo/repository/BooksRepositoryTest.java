package com.LibroFlow.demo.repository;

import com.LibroFlow.demo.dtos.BooksDTO;
import com.LibroFlow.demo.entities.Books;
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
class BooksRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    BooksRepository booksRepository;

    @Test
    @Transactional
    @DisplayName("Deve encontrar um livro por seu titulo")
    void findByTitleSuccess() {
        BooksDTO dto = new BooksDTO( "Titulo", "Autor", Genre.AVENTURA, "Descricao", 10, true);
        Books book = createUser(dto);
        Books found = booksRepository.findByTitle(dto.getTitle());
        assertEquals(book.getId(), found.getId());
    }

    @Test
    @Transactional
    @DisplayName("Não deve encontrar um livro por seu titulo")
    void dontFindByTitle() {
        BooksDTO dto = new BooksDTO( "Titulo", "Autor", Genre.AVENTURA, "Descricao", 10, true);
        createUser(dto);
        Books found = booksRepository.findByTitle("Titulo errado");
        assertNull(found);
    }

    private Books createUser(BooksDTO dto) {
        Books book = new Books(dto);
        entityManager.persist(book);
        return book;
    }
}