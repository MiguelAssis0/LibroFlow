package com.LibroFlow.demo.service;

import com.LibroFlow.demo.dtos.BooksDTO;
import com.LibroFlow.demo.dtos.BorrowedBooksDTO;
import com.LibroFlow.demo.dtos.UserDTO;
import com.LibroFlow.demo.entities.Books;
import com.LibroFlow.demo.entities.BorrowedBooks;
import com.LibroFlow.demo.entities.User;
import com.LibroFlow.demo.repository.BooksRepository;
import com.LibroFlow.demo.repository.BorrowedBooksRepository;
import com.LibroFlow.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowedBooksService {
    @Autowired
    private BorrowedBooksRepository borrowedBooksRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BooksRepository booksRepository;

    public BorrowedBooksDTO createBorrowedBooks(BorrowedBooksDTO dto) {
        User user = userRepository.findById(dto.getUser().getId()).orElse(null);
        Books books = booksRepository.findById(dto.getBook().getId()).orElse(null);
        BorrowedBooks borrowedBooks = new BorrowedBooks(dto, user, books);
        borrowedBooksRepository.save(borrowedBooks);
        return new BorrowedBooksDTO(borrowedBooks);
    }


    public BorrowedBooksDTO getBorrowedBooks(Long id) {
        BorrowedBooks borrowedBooks = (BorrowedBooks) borrowedBooksRepository.findAll();
        return new BorrowedBooksDTO(borrowedBooks);
    }


    public BorrowedBooksDTO getBorrowedBooksByUser(User user) {
        BorrowedBooks borrowedBooks = borrowedBooksRepository.findByUser(user);
        return new BorrowedBooksDTO(borrowedBooks);
    }
}
