package com.LibroFlow.demo.service;

import com.LibroFlow.demo.dtos.BorrowedBooksDTO;
import com.LibroFlow.demo.dtos.BorrowedBooksProjectionDTO;
import com.LibroFlow.demo.entities.Books;
import com.LibroFlow.demo.entities.BorrowedBooks;
import com.LibroFlow.demo.entities.User;
import com.LibroFlow.demo.projections.BorrowedBooksProjection;
import com.LibroFlow.demo.repository.BooksRepository;
import com.LibroFlow.demo.repository.BorrowedBooksRepository;
import com.LibroFlow.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BorrowedBooksService {
    @Autowired
    private BorrowedBooksRepository borrowedBooksRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BooksRepository booksRepository;

    public BorrowedBooksDTO createBorrowedBooks(BorrowedBooksDTO borrowedBooksDTO) {
        User user = new User();
        user.setId(borrowedBooksDTO.getUserId());
        Books book = booksRepository.findById(borrowedBooksDTO.getBookId()).orElseThrow(RuntimeException::new);
        if(book.getQuantity() < 1){
            throw new RuntimeException("Livro indisponivel");
        }
        book.setQuantity(book.getQuantity() - 1);

        booksRepository.save(book);
        BorrowedBooks borrowedBooks = new BorrowedBooks(borrowedBooksDTO, user, book);
        borrowedBooksRepository.save(borrowedBooks);

        return new BorrowedBooksDTO(borrowedBooks);
    }

    public Page<BorrowedBooksProjectionDTO> getAllBorrowedBooks(Pageable pageable) {
        Page<BorrowedBooksProjection> borrowBooks = borrowedBooksRepository.findAllBorrowedBooks(pageable);
        return borrowBooks.map(BorrowedBooksProjectionDTO::new);
    }

    public Page<BorrowedBooksProjectionDTO> getAllReturnedBooks(Pageable pageable) {
        Page<BorrowedBooksProjection> returnedBooks = borrowedBooksRepository.findAllReturnedBooks(pageable);
        return returnedBooks.map(BorrowedBooksProjectionDTO::new);
    }

    public void returnBook(String username, String title){
        User user = userRepository.findByUsername(username);
        Books book = booksRepository.findByTitle(title);
        BorrowedBooks borrowedBooks = borrowedBooksRepository.findByUserAndBook(user.getId(), book.getId());
        book.setQuantity(book.getQuantity() + 1);
        borrowedBooks.setIsReturned(true);
        borrowedBooksRepository.save(borrowedBooks);
        booksRepository.save(book);
    }
}