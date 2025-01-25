package com.LibroFlow.demo.controllers;

import com.LibroFlow.demo.dtos.BorrowBooksDTO;
import com.LibroFlow.demo.dtos.BorrowBooksProjectionDTO;
import com.LibroFlow.demo.dtos.ReturnBookDTO;
import com.LibroFlow.demo.service.BorrowBooksService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/borrowbooks")
@SecurityRequirement(name = "bearer-key")
public class BorrowBooksController {
    @Autowired
    private BorrowBooksService borrowBooksService;

    @PostMapping
    public ResponseEntity<BorrowBooksDTO> createBorrowedBooks(@Valid @RequestBody BorrowBooksDTO borrowBooksDTO) {
        BorrowBooksDTO borrowedBooks = borrowBooksService.createBorrowedBooks(borrowBooksDTO);
        URI address = URI.create("/borrowbooks/" + borrowedBooks);
        return ResponseEntity.created(address).body(borrowedBooks);
    }

    @GetMapping
    public ResponseEntity<List<BorrowBooksProjectionDTO>> getBorrowedBooks() {
        List<BorrowBooksProjectionDTO> borrowedBooks = borrowBooksService.getAllBorrowedBooks();
        return ResponseEntity.ok(borrowedBooks);
    }

    @GetMapping("/return")
    public ResponseEntity<List<BorrowBooksProjectionDTO>> getReturnedBooks() {
        List<BorrowBooksProjectionDTO> borrowedBooks = borrowBooksService.getAllReturnedBooks();
        return ResponseEntity.ok(borrowedBooks);
    }

    @PostMapping("/return")
    public ResponseEntity<Void> returnBook(@Valid @RequestBody ReturnBookDTO returnBookDTO) {
        borrowBooksService.returnBook(returnBookDTO);
        return ResponseEntity.noContent().build();
    }




}
