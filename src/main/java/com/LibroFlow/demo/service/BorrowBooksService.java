package com.LibroFlow.demo.service;

import com.LibroFlow.demo.dtos.BorrowBooksDTO;
import com.LibroFlow.demo.dtos.BorrowBooksProjectionDTO;
import com.LibroFlow.demo.dtos.ReturnBookDTO;
import com.LibroFlow.demo.entities.Books;
import com.LibroFlow.demo.entities.BorrowBooks;
import com.LibroFlow.demo.entities.User;
import com.LibroFlow.demo.infra.exceptions.EventFullException;
import com.LibroFlow.demo.infra.exceptions.EventNotFoundException;
import com.LibroFlow.demo.projections.BorrowBooksProjection;
import com.LibroFlow.demo.repository.BooksRepository;
import com.LibroFlow.demo.repository.BorrowBooksRepository;
import com.LibroFlow.demo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowBooksService {
    @Autowired
    private BorrowBooksRepository borrowBooksRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BooksRepository booksRepository;

    public BorrowBooksDTO createBorrowedBooks(BorrowBooksDTO borrowBooksDTO) {

        User user = userRepository.findById(borrowBooksDTO.getUserId())
                .orElseThrow(() -> new EventNotFoundException("Usuário não encontrado"));

        Books book = booksRepository.findById(borrowBooksDTO.getBookId())
                .orElseThrow(() -> new EventNotFoundException("Livro não encontrado"));

        if (book.getQuantity() < 1) throw new EventFullException("Livro indisponivel");
        book.setQuantity(book.getQuantity() - 1);
        booksRepository.save(book);

        BorrowBooks borrowBooks = new BorrowBooks(borrowBooksDTO, user, book);
        borrowBooksRepository.save(borrowBooks);
        return new BorrowBooksDTO(borrowBooks);

    }

    public List<BorrowBooksProjectionDTO> getAllBorrowedBooks() {
        List<BorrowBooksProjection> borrowBooks = borrowBooksRepository.findAllBorrowedBooks();
        if(borrowBooks.isEmpty()) throw new EventNotFoundException("Nenhum empréstimo encontrado");
        return borrowBooks.stream().map(BorrowBooksProjectionDTO::new).toList();
    }

    public List<BorrowBooksProjectionDTO> getAllReturnedBooks() {
        List<BorrowBooksProjection> returnedBooks = borrowBooksRepository.findAllReturnedBooks();
        if(returnedBooks.isEmpty()) throw new EventNotFoundException("Não há empréstimos retornados");
        return returnedBooks.stream().map(BorrowBooksProjectionDTO::new).toList();
    }

    public void returnBook(ReturnBookDTO dto){
        User user = userRepository.findByUsername(dto.getUsername());
        if(user == null) throw new EventNotFoundException("Usuário nao encontrado");
        Books book = booksRepository.findByTitle(dto.getTitle());
        if(book == null) throw new EventNotFoundException("Livro nao encontrado");
        BorrowBooks borrowBooks = borrowBooksRepository.findByUserAndBook(user.getId(), book.getId());
        if(borrowBooks == null) throw new EventNotFoundException("Empréstimo não existe");
        book.setQuantity(book.getQuantity() + 1);
        borrowBooks.setIsReturned(true);
        borrowBooksRepository.save(borrowBooks);
        booksRepository.save(book);
    }
}