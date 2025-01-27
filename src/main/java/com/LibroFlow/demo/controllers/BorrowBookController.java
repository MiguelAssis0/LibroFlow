package com.LibroFlow.demo.controllers;

import com.LibroFlow.demo.dtos.BorrowBookDTO;
import com.LibroFlow.demo.dtos.BorrowBookProjectionDTO;
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
@RequestMapping("/borrowbook")
@SecurityRequirement(name = "bearer-key")
public class BorrowBookController {
    @Autowired
    private BorrowBooksService borrowBooksService;

    @PostMapping
    public ResponseEntity<BorrowBookDTO> createBorrowedBooks(@Valid @RequestBody BorrowBookDTO borrowBookDTO) {
        BorrowBookDTO borrowedBooks = borrowBooksService.createBorrowedBooks(borrowBookDTO);
        URI address = URI.create("/borrowbook/" + borrowedBooks);
        return ResponseEntity.created(address).body(borrowedBooks);
    }

    @GetMapping
    public ResponseEntity<List<BorrowBookProjectionDTO>> getBorrowBook() {
        List<BorrowBookProjectionDTO> borrowedBooks = borrowBooksService.getAllBorrowedBooks();
        return ResponseEntity.ok(borrowedBooks);
    }

    @GetMapping("/return")
    public ResponseEntity<List<BorrowBookProjectionDTO>> getReturnBook() {
        List<BorrowBookProjectionDTO> borrowedBooks = borrowBooksService.getAllReturnedBooks();
        return ResponseEntity.ok(borrowedBooks);
    }

    @PostMapping("/return")
    public ResponseEntity<Void> returnBook(@Valid @RequestBody ReturnBookDTO returnBookDTO) {
        borrowBooksService.returnBook(returnBookDTO);
        return ResponseEntity.noContent().build();
    }




}
