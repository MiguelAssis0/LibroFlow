package com.LibroFlow.demo.service;

import com.LibroFlow.demo.dtos.BooksDTO;
import com.LibroFlow.demo.dtos.BorrowedBooksDTO;
import com.LibroFlow.demo.dtos.BorrowedBooksProjectionDTO;
import com.LibroFlow.demo.dtos.UserDTO;
import com.LibroFlow.demo.entities.Books;
import com.LibroFlow.demo.entities.BorrowedBooks;
import com.LibroFlow.demo.entities.User;
import com.LibroFlow.demo.projections.BorrowedBooksProjection;
import com.LibroFlow.demo.repository.BooksRepository;
import com.LibroFlow.demo.repository.BorrowedBooksRepository;
import com.LibroFlow.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

        Books book = new Books();
        book.setId(borrowedBooksDTO.getBookId());

        BorrowedBooks borrowedBooks = new BorrowedBooks(borrowedBooksDTO, user, book);

        borrowedBooksRepository.save(borrowedBooks);

        return new BorrowedBooksDTO(borrowedBooks);

    }

    public Page<BorrowedBooksProjectionDTO> getAllBorrowedBooks(Pageable pageable) {
        Page<BorrowedBooksProjection> borrowBooks = borrowedBooksRepository.findAllBorrowedBooks(pageable);
        return borrowBooks.map(BorrowedBooksProjectionDTO::new);
    }

 }



