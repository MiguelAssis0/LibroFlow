package com.LibroFlow.demo.controllers;

import com.LibroFlow.demo.dtos.BorrowBooksDTO;
import com.LibroFlow.demo.dtos.BorrowBooksProjectionDTO;
import com.LibroFlow.demo.dtos.ReturnBookDTO;
import com.LibroFlow.demo.service.BorrowBooksService;
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
public class BorrowBooksController {
    @Autowired
    private BorrowBooksService borrowBooksService;

    @PostMapping
    public ResponseEntity<BorrowBooksDTO> createBorrowedBooks(@Validated @RequestBody BorrowBooksDTO borrowBooksDTO) {
        BorrowBooksDTO borrowedBooks = borrowBooksService.createBorrowedBooks(borrowBooksDTO);
        URI address = URI.create("/borrowbooks/" + borrowedBooks);
        return ResponseEntity.created(address).body(borrowedBooks);
    }

    @GetMapping
    public ResponseEntity<Page<BorrowBooksProjectionDTO>> getBorrowedBooks(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<BorrowBooksProjectionDTO> borrowedBooks = borrowBooksService.getAllBorrowedBooks(pageable);
        return ResponseEntity.ok(borrowedBooks);
    }

    @GetMapping("/return")
    public ResponseEntity<Page<BorrowBooksProjectionDTO>> getReturnedBooks(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<BorrowBooksProjectionDTO> borrowedBooks = borrowBooksService.getAllReturnedBooks(pageable);
        return ResponseEntity.ok(borrowedBooks);
    }

    @PostMapping("/return")
    public ResponseEntity<Void> returnBook(@RequestBody ReturnBookDTO returnBookDTO) {
        borrowBooksService.returnBook(returnBookDTO);
        return ResponseEntity.noContent().build();
    }




}
