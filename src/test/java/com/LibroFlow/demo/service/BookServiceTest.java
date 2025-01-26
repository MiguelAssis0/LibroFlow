package com.LibroFlow.demo.service;

import com.LibroFlow.demo.dtos.BooksDTO;
import com.LibroFlow.demo.entities.Books;
import com.LibroFlow.demo.enums.Genre;
import com.LibroFlow.demo.infra.exceptions.EventNotFoundException;
import com.LibroFlow.demo.repository.BooksRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BooksRepository bookRepository;



    @Test
    @Transactional
    @DisplayName("Deve criar um livro")
    void createBookSuccess() {
        BooksDTO dto = new BooksDTO( "Titulo", "Autor", Genre.AVENTURA, "descricao", 10, true);
        bookService.createBook(dto);
        verify(bookRepository, times(1)).save(any(Books.class));

    }

    @Test
    @Transactional
    @DisplayName("Deve encontrar todos os livros cadastrados")
    void findAllBooksSuccess() {
        List<Books> books = new ArrayList<>();
        books.add(new Books(new BooksDTO("Titulo", "Autor", Genre.AVENTURA, "descricao", 10, true)));

        when(bookRepository.findAll()).thenReturn(books);

        List<BooksDTO> booksDTO = bookService.findAllBooks();

        assertEquals(1, booksDTO.size());
    }


    @Test
    @Transactional
    @DisplayName("Deve retornar uma lista vazia de livros")
    void returnThrowEventNotFoundException() {
        assertThrows(EventNotFoundException.class, () -> {
            List<BooksDTO> books = bookService.findAllBooks();
        });;
    }

    @Test
    @Transactional
    @DisplayName("Deve encontrar um livro por seu titulo")
    void findByTitleSuccess() {
        List<Books> books = new ArrayList<>();
        books.add(new Books(new BooksDTO("Titulo", "Autor", Genre.AVENTURA, "descricao", 10, true)));
        when(bookRepository.findByTitle(books.get(0).getTitle())).thenReturn(books.get(0));
        BooksDTO booksDTO = bookService.findByTitle(books.get(0).getTitle());
        assertEquals(books.get(0).getTitle(), booksDTO.getTitle());
    }

    @Test
    @Transactional
    @DisplayName("Deve retornar uma exception para livro inexistente")
    void shouldReturnEventNotFoundWhenBookDoesNotExist() {
        List<Books> books = new ArrayList<>();
        books.add(new Books(new BooksDTO("Titulo", "Autor", Genre.AVENTURA, "descricao", 10, true)));
        assertThrows(EventNotFoundException.class, () -> {
            bookService.findByTitle("Titulo errado");
        });
    }

    @Test
    @Transactional
    @DisplayName("Deve deletar um livro")
    void deleteBookSuccess() {
        List<Books> books = new ArrayList<>();
        books.add(new Books(new BooksDTO("Titulo", "Autor", Genre.AVENTURA, "descricao", 10, true)));
        when(bookRepository.findById(books.get(0).getId())).thenReturn(Optional.of(books.get(0)));
        bookService.deleteBook(books.get(0).getId());
        verify(bookRepository, times(1)).delete(any(Books.class));
    }

    @Test
    @Transactional
    @DisplayName("Deve retornar uma exception para livro inexistente")
    void shouldReturnExceptionWhenBookDoesNotExist() {
        List<Books> books = new ArrayList<>();
        books.add(new Books(new BooksDTO("Titulo", "Autor", Genre.AVENTURA, "descricao", 10, true)));
        when(bookRepository.findById(books.get(0).getId())).thenReturn(Optional.empty());
        assertThrows(EventNotFoundException.class, () -> {
            bookService.deleteBook(books.get(0).getId());
        });
    }

    @Test
    @Transactional
    @DisplayName("Deve atualizar um livro")
    void updateBook() {
        List<Books> books = new ArrayList<>();
        books.add(new Books(new BooksDTO("Titulo", "Autor", Genre.AVENTURA, "descricao", 10, true)));
        when(bookRepository.findById(books.get(0).getId())).thenReturn(Optional.of(books.get(0)));
        bookService.updateBook(books.get(0).getId(), new BooksDTO("Novo Titulo", "Novo Autor", Genre.AVENTURA, "Nova Descricao", 10, true));
        verify(bookRepository, times(1)).save(any(Books.class));
    }
}