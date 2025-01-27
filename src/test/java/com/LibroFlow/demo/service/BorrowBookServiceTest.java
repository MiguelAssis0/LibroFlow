package com.LibroFlow.demo.service;

import com.LibroFlow.demo.dtos.*;
import com.LibroFlow.demo.entities.Book;
import com.LibroFlow.demo.entities.BorrowBook;
import com.LibroFlow.demo.entities.BorrowBookPK;
import com.LibroFlow.demo.entities.User;
import com.LibroFlow.demo.enums.Genre;
import com.LibroFlow.demo.enums.UserRole;
import com.LibroFlow.demo.infra.exceptions.EventNotFoundException;
import com.LibroFlow.demo.projections.BorrowBookProjection;
import com.LibroFlow.demo.repository.BookRepository;
import com.LibroFlow.demo.repository.BorrowBookRepository;
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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class BorrowBookServiceTest {

    @InjectMocks
    private BorrowBooksService borrowBooksService;


    @Mock
    private BorrowBookRepository borrowBookRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BookRepository bookRepository;

    @Test
    @Transactional
    @DisplayName("Deve criar um empréstimo de livro com o usuário e o livro")
    void createBorrowedBooksSuccess() {
        Book book = new Book(new BookDTO("Titulo", "Autor", Genre.AVENTURA, "descricao", 10, true));
        book.setId(1L);

        User user = new User(new UserDTO("username", "password", "email", UserRole.USER));
        user.setId(1L);

        BorrowBookDTO borrowBookDTO = new BorrowBookDTO(book.getId(), user.getId());

        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(borrowBookRepository.save(any(BorrowBook.class))).thenAnswer(invocation -> invocation.getArgument(0));

        BorrowBookDTO result = borrowBooksService.createBorrowedBooks(borrowBookDTO);

        assertNotNull(result, "O resultado não deve ser nulo");
        assertEquals(borrowBookDTO.getBookId(), result.getBookId(), "O ID do livro deve ser o mesmo");
        assertEquals(borrowBookDTO.getUserId(), result.getUserId(), "O ID do usuário deve ser o mesmo");

        assertEquals(9, book.getQuantity(), "A quantidade do livro deve ser reduzida em 1");

        verify(bookRepository).findById(book.getId());
        verify(userRepository).findById(user.getId());
        verify(bookRepository).save(book);
        verify(borrowBookRepository).save(any(BorrowBook.class));
    }

    @Test
    @Transactional
    @DisplayName("Deve lançar exceção quando o usuário não for encontrado")
    void createBorrowedBooksUserNotFound() {
        // Mockando o DTO de empréstimo
        BorrowBookDTO borrowBookDTO = new BorrowBookDTO(1L, 1L);

        // Mockando o repositório para retornar vazio no usuário
        when(userRepository.findById(borrowBookDTO.getUserId())).thenReturn(Optional.empty());

        // Executando e verificando a exceção
        EventNotFoundException exception = assertThrows(EventNotFoundException.class,
                () -> borrowBooksService.createBorrowedBooks(borrowBookDTO));

        assertEquals("Usuário não encontrado", exception.getMessage());
        verify(userRepository).findById(borrowBookDTO.getUserId());
        verifyNoInteractions(bookRepository, borrowBookRepository);
    }

    @Test
    @Transactional
    @DisplayName("Deve lançar exceção quando o livro não for encontrado")
    void createBorrowedBooksBookNotFound() {
        BorrowBookDTO borrowBookDTO = new BorrowBookDTO(1L, 1L);

        User user = new User(new UserDTO("username", "password", "email", UserRole.USER));
        user.setId(1L);
        when(userRepository.findById(borrowBookDTO.getUserId())).thenReturn(Optional.of(user));

        when(bookRepository.findById(borrowBookDTO.getBookId())).thenReturn(Optional.empty());

        EventNotFoundException exception = assertThrows(EventNotFoundException.class,
                () -> borrowBooksService.createBorrowedBooks(borrowBookDTO));

        assertEquals("Livro não encontrado", exception.getMessage());
        verify(userRepository).findById(borrowBookDTO.getUserId());
        verify(bookRepository).findById(borrowBookDTO.getBookId());
        verifyNoInteractions(borrowBookRepository);
    }


    @Test
    @Transactional
    @DisplayName("Deve retornar uma lista de empréstimos")
    void getAllBorrowedBooks() {
    BorrowBookProjection projection = mock(BorrowBookProjection.class);
    when(projection.getBorrowDate()).thenReturn(LocalDate.now());
    when(projection.getReturnDate()).thenReturn(LocalDate.now().plusDays(10));
    when(projection.getUserName()).thenReturn("username");
    when(projection.getBookTitle()).thenReturn("Titulo");
    when(projection.getIsReturned()).thenReturn(false);

    List<BorrowBookProjection> projections = List.of(projection);
    when(borrowBookRepository.findAllBorrowedBooks()).thenReturn(projections);

    List<BorrowBookProjectionDTO> result = borrowBooksService.getAllBorrowedBooks();

    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals("username", result.get(0).getUserName());
    assertEquals("Titulo", result.get(0).getBookTitle());
    assertFalse(result.get(0).getIsReturned());
    verify(borrowBookRepository).findAllBorrowedBooks();

    }

    @Test
    @Transactional
    @DisplayName("Deve lançar exceção quando não houver empréstimos")
    void getAllBorrowedBooksThrowsExceptionWhenEmpty() {
        when(borrowBookRepository.findAllBorrowedBooks()).thenReturn(List.of());

        EventNotFoundException exception = assertThrows(EventNotFoundException.class,
                () -> borrowBooksService.getAllBorrowedBooks());

        assertEquals("Nenhum empréstimo encontrado", exception.getMessage(),
                "A mensagem da exceção deve ser 'Nenhum empréstimo encontrado'");

        verify(borrowBookRepository).findAllBorrowedBooks();
    }

    @Test
    @Transactional
    @DisplayName("Deve retornar uma lista de empréstimos retornados")
    void getAllReturnedBooks() {
        BorrowBookProjection projection = mock(BorrowBookProjection.class);
        when(projection.getBorrowDate()).thenReturn(LocalDate.now());
        when(projection.getReturnDate()).thenReturn(LocalDate.now().plusDays(10));
        when(projection.getUserName()).thenReturn("username");
        when(projection.getBookTitle()).thenReturn("Titulo");
        when(projection.getIsReturned()).thenReturn(true);

        List<BorrowBookProjection> projections = List.of(projection);
        when(borrowBookRepository.findAllReturnedBooks()).thenReturn(projections);

        List<BorrowBookProjectionDTO> result = borrowBooksService.getAllReturnedBooks();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("username", result.get(0).getUserName());
        assertEquals("Titulo", result.get(0).getBookTitle());
        assertTrue(result.get(0).getIsReturned());
        verify(borrowBookRepository).findAllReturnedBooks();
    }


    @Test
    @Transactional
    @DisplayName("Deve lançar exceção quando não houver empréstimos retornados")
    void getAllReturnedBooksThrowsExceptionWhenEmpty() {
        when(borrowBookRepository.findAllReturnedBooks()).thenReturn(List.of());

        EventNotFoundException exception = assertThrows(EventNotFoundException.class,
                () -> borrowBooksService.getAllReturnedBooks());

        assertEquals("Não há empréstimos retornados", exception.getMessage(),
                "A mensagem da exceção deve ser 'Nenhum empréstimo retornado encontrado'");

        verify(borrowBookRepository).findAllReturnedBooks();
    }

    @Test
    @Transactional
    @DisplayName("Deve retornar um empréstimo")
    void returnBook() {
        ReturnBookDTO returnBookDTO = new ReturnBookDTO("username", "Titulo");

        User user = new User(new UserDTO("username", "password", "email", UserRole.USER));
        user.setId(1L);

        Book book = new Book(new BookDTO("Titulo", "Autor", Genre.AVENTURA, "descricao", 10, true));
        book.setId(2L);

        when(userRepository.findByUsername(returnBookDTO.getUsername())).thenReturn(user);
        when(bookRepository.findByTitle(returnBookDTO.getTitle())).thenReturn(book);

        BorrowBookDTO borrowBookDTO = new BorrowBookDTO(user.getId(), book.getId(), LocalDate.now(), LocalDate.now().plusDays(10));

        BorrowBook borrowBook = new BorrowBook(borrowBookDTO, user, book);
        borrowBook.setId(new BorrowBookPK(user.getId(), book.getId()));

        when(borrowBookRepository.findByUserAndBook(user.getId(), book.getId())).thenReturn(borrowBook);
        when(borrowBookRepository.save(any(BorrowBook.class))).thenAnswer(invocation -> invocation.getArgument(0));

        borrowBooksService.returnBook(returnBookDTO);

        verify(borrowBookRepository).findByUserAndBook(user.getId(), book.getId());
        verify(borrowBookRepository).save(any(BorrowBook.class));
    }
}