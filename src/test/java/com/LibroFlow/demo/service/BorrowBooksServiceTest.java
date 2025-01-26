package com.LibroFlow.demo.service;

import com.LibroFlow.demo.dtos.*;
import com.LibroFlow.demo.entities.Books;
import com.LibroFlow.demo.entities.BorrowBooks;
import com.LibroFlow.demo.entities.BorrowBooksPK;
import com.LibroFlow.demo.entities.User;
import com.LibroFlow.demo.enums.Genre;
import com.LibroFlow.demo.enums.UserRole;
import com.LibroFlow.demo.infra.exceptions.EventNotFoundException;
import com.LibroFlow.demo.projections.BorrowBooksProjection;
import com.LibroFlow.demo.repository.BooksRepository;
import com.LibroFlow.demo.repository.BorrowBooksRepository;
import com.LibroFlow.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class BorrowBooksServiceTest {

    @InjectMocks
    private BorrowBooksService borrowBooksService;


    @Mock
    private BorrowBooksRepository borrowBooksRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BooksRepository booksRepository;

    @Test
    @Transactional
    @DisplayName("Deve criar um empréstimo de livro com o usuário e o livro")
    void createBorrowedBooksSuccess() {
        Books book = new Books(new BooksDTO("Titulo", "Autor", Genre.AVENTURA, "descricao", 10, true));
        book.setId(1L);

        User user = new User(new UserDTO("username", "password", "email", UserRole.USER));
        user.setId(1L);

        BorrowBooksDTO borrowBooksDTO = new BorrowBooksDTO(book.getId(), user.getId());

        when(booksRepository.findById(book.getId())).thenReturn(Optional.of(book));
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(borrowBooksRepository.save(any(BorrowBooks.class))).thenAnswer(invocation -> invocation.getArgument(0));

        BorrowBooksDTO result = borrowBooksService.createBorrowedBooks(borrowBooksDTO);

        assertNotNull(result, "O resultado não deve ser nulo");
        assertEquals(borrowBooksDTO.getBookId(), result.getBookId(), "O ID do livro deve ser o mesmo");
        assertEquals(borrowBooksDTO.getUserId(), result.getUserId(), "O ID do usuário deve ser o mesmo");

        assertEquals(9, book.getQuantity(), "A quantidade do livro deve ser reduzida em 1");

        verify(booksRepository).findById(book.getId());
        verify(userRepository).findById(user.getId());
        verify(booksRepository).save(book);
        verify(borrowBooksRepository).save(any(BorrowBooks.class));
    }

    @Test
    @Transactional
    @DisplayName("Deve lançar exceção quando o usuário não for encontrado")
    void createBorrowedBooksUserNotFound() {
        // Mockando o DTO de empréstimo
        BorrowBooksDTO borrowBooksDTO = new BorrowBooksDTO(1L, 1L);

        // Mockando o repositório para retornar vazio no usuário
        when(userRepository.findById(borrowBooksDTO.getUserId())).thenReturn(Optional.empty());

        // Executando e verificando a exceção
        EventNotFoundException exception = assertThrows(EventNotFoundException.class,
                () -> borrowBooksService.createBorrowedBooks(borrowBooksDTO));

        assertEquals("Usuário não encontrado", exception.getMessage());
        verify(userRepository).findById(borrowBooksDTO.getUserId());
        verifyNoInteractions(booksRepository, borrowBooksRepository);
    }

    @Test
    @Transactional
    @DisplayName("Deve lançar exceção quando o livro não for encontrado")
    void createBorrowedBooksBookNotFound() {
        BorrowBooksDTO borrowBooksDTO = new BorrowBooksDTO(1L, 1L);

        User user = new User(new UserDTO("username", "password", "email", UserRole.USER));
        user.setId(1L);
        when(userRepository.findById(borrowBooksDTO.getUserId())).thenReturn(Optional.of(user));

        when(booksRepository.findById(borrowBooksDTO.getBookId())).thenReturn(Optional.empty());

        EventNotFoundException exception = assertThrows(EventNotFoundException.class,
                () -> borrowBooksService.createBorrowedBooks(borrowBooksDTO));

        assertEquals("Livro não encontrado", exception.getMessage());
        verify(userRepository).findById(borrowBooksDTO.getUserId());
        verify(booksRepository).findById(borrowBooksDTO.getBookId());
        verifyNoInteractions(borrowBooksRepository);
    }


    @Test
    @Transactional
    @DisplayName("Deve retornar uma lista de empréstimos")
    void getAllBorrowedBooks() {
    BorrowBooksProjection projection = mock(BorrowBooksProjection.class);
    when(projection.getBorrowDate()).thenReturn(LocalDate.now());
    when(projection.getReturnDate()).thenReturn(LocalDate.now().plusDays(10));
    when(projection.getUserName()).thenReturn("username");
    when(projection.getBookTitle()).thenReturn("Titulo");
    when(projection.getIsReturned()).thenReturn(false);

    List<BorrowBooksProjection> projections = List.of(projection);
    when(borrowBooksRepository.findAllBorrowedBooks()).thenReturn(projections);

    List<BorrowBooksProjectionDTO> result = borrowBooksService.getAllBorrowedBooks();

    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals("username", result.get(0).getUserName());
    assertEquals("Titulo", result.get(0).getBookTitle());
    assertFalse(result.get(0).getIsReturned());
    verify(borrowBooksRepository).findAllBorrowedBooks();

    }

    @Test
    @Transactional
    @DisplayName("Deve lançar exceção quando não houver empréstimos")
    void getAllBorrowedBooksThrowsExceptionWhenEmpty() {
        when(borrowBooksRepository.findAllBorrowedBooks()).thenReturn(List.of());

        EventNotFoundException exception = assertThrows(EventNotFoundException.class,
                () -> borrowBooksService.getAllBorrowedBooks());

        assertEquals("Nenhum empréstimo encontrado", exception.getMessage(),
                "A mensagem da exceção deve ser 'Nenhum empréstimo encontrado'");

        verify(borrowBooksRepository).findAllBorrowedBooks();
    }

    @Test
    @Transactional
    @DisplayName("Deve retornar uma lista de empréstimos retornados")
    void getAllReturnedBooks() {
        BorrowBooksProjection projection = mock(BorrowBooksProjection.class);
        when(projection.getBorrowDate()).thenReturn(LocalDate.now());
        when(projection.getReturnDate()).thenReturn(LocalDate.now().plusDays(10));
        when(projection.getUserName()).thenReturn("username");
        when(projection.getBookTitle()).thenReturn("Titulo");
        when(projection.getIsReturned()).thenReturn(true);

        List<BorrowBooksProjection> projections = List.of(projection);
        when(borrowBooksRepository.findAllReturnedBooks()).thenReturn(projections);

        List<BorrowBooksProjectionDTO> result = borrowBooksService.getAllReturnedBooks();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("username", result.get(0).getUserName());
        assertEquals("Titulo", result.get(0).getBookTitle());
        assertTrue(result.get(0).getIsReturned());
        verify(borrowBooksRepository).findAllReturnedBooks();
    }


    @Test
    @Transactional
    @DisplayName("Deve lançar exceção quando não houver empréstimos retornados")
    void getAllReturnedBooksThrowsExceptionWhenEmpty() {
        when(borrowBooksRepository.findAllReturnedBooks()).thenReturn(List.of());

        EventNotFoundException exception = assertThrows(EventNotFoundException.class,
                () -> borrowBooksService.getAllReturnedBooks());

        assertEquals("Não há empréstimos retornados", exception.getMessage(),
                "A mensagem da exceção deve ser 'Nenhum empréstimo retornado encontrado'");

        verify(borrowBooksRepository).findAllReturnedBooks();
    }

    @Test
    @Transactional
    @DisplayName("Deve retornar um empréstimo")
    void returnBook() {
        ReturnBookDTO returnBookDTO = new ReturnBookDTO("username", "Titulo");

        User user = new User(new UserDTO("username", "password", "email", UserRole.USER));
        user.setId(1L);

        Books book = new Books(new BooksDTO("Titulo", "Autor", Genre.AVENTURA, "descricao", 10, true));
        book.setId(2L);

        when(userRepository.findByUsername(returnBookDTO.getUsername())).thenReturn(user);
        when(booksRepository.findByTitle(returnBookDTO.getTitle())).thenReturn(book);

        BorrowBooksDTO borrowBooksDTO = new BorrowBooksDTO(user.getId(), book.getId(), LocalDate.now(), LocalDate.now().plusDays(10));

        BorrowBooks borrowBooks = new BorrowBooks(borrowBooksDTO, user, book);
        borrowBooks.setId(new BorrowBooksPK(user.getId(), book.getId()));

        when(borrowBooksRepository.findByUserAndBook(user.getId(), book.getId())).thenReturn(borrowBooks);
        when(borrowBooksRepository.save(any(BorrowBooks.class))).thenAnswer(invocation -> invocation.getArgument(0));

        borrowBooksService.returnBook(returnBookDTO);

        verify(borrowBooksRepository).findByUserAndBook(user.getId(), book.getId());
        verify(borrowBooksRepository).save(any(BorrowBooks.class));
    }
}