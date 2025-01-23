package com.LibroFlow.demo.controllers;

import com.LibroFlow.demo.dtos.BorrowedBooksDTO;
import com.LibroFlow.demo.dtos.BorrowedBooksProjectionDTO;
import com.LibroFlow.demo.dtos.ReturnBookDTO;
import com.LibroFlow.demo.entities.BorrowedBooks;
import com.LibroFlow.demo.entities.User;
import com.LibroFlow.demo.service.BorrowedBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/borrowbooks")
public class BorrowedBooksController {
    @Autowired
    private BorrowedBooksService borrowedBooksService;

    @PostMapping
    public ResponseEntity<BorrowedBooksDTO> createBorrowedBooks(@Validated @RequestBody BorrowedBooksDTO borrowedBooksDTO) {
        BorrowedBooksDTO borrowedBooks = borrowedBooksService.createBorrowedBooks(borrowedBooksDTO);
        URI address = URI.create("/borrowedbooks/" + borrowedBooks);
        return ResponseEntity.created(address).body(borrowedBooks);
    }

    @GetMapping
    public ResponseEntity<Page<BorrowedBooksProjectionDTO>> getBorrowedBooks(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<BorrowedBooksProjectionDTO> borrowedBooks = borrowedBooksService.getAllBorrowedBooks(pageable);
        return ResponseEntity.ok(borrowedBooks);
    }

    @PostMapping("/return")
    public ResponseEntity<Void> returnBook(@RequestBody ReturnBookDTO returnBookDTO) {
        borrowedBooksService.returnBook(returnBookDTO.getUsername(), returnBookDTO.getTitle());
        return ResponseEntity.noContent().build();
    }



}
