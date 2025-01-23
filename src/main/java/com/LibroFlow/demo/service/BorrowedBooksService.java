package com.LibroFlow.demo.service;

import com.LibroFlow.demo.dtos.BorrowedBooksDTO;
import com.LibroFlow.demo.entities.BorrowedBooks;
import com.LibroFlow.demo.repository.BorrowedBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowedBooksService {
    @Autowired
    private BorrowedBooksRepository borrowedBooksRepository;

    public BorrowedBooksDTO createBorrowedBooks(BorrowedBooksDTO dto) {
        BorrowedBooks borrowedBooks = new BorrowedBooks(dto);
        borrowedBooksRepository.save(borrowedBooks);
        return new BorrowedBooksDTO(borrowedBooks);
    }
}
