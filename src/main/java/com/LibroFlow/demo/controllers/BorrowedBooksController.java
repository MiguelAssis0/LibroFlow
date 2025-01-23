package com.LibroFlow.demo.controllers;

import com.LibroFlow.demo.dtos.BorrowedBooksDTO;
import com.LibroFlow.demo.entities.User;
import com.LibroFlow.demo.service.BorrowedBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/borrowedbooks")
public class BorrowedBooksController {
    @Autowired
    private BorrowedBooksService borrowedBooksService;

    @PostMapping
    public ResponseEntity<BorrowedBooksDTO> createBorrowedBooks(@Validated @RequestBody BorrowedBooksDTO dto) {
        BorrowedBooksDTO borrowedBooks = borrowedBooksService.createBorrowedBooks(dto);
        URI address = URI.create("/borrowedbooks/" + borrowedBooks.getId());
        return ResponseEntity.created(address).body(borrowedBooks);
    }

    @GetMapping
    public ResponseEntity<BorrowedBooksDTO> getBorrowedBooks(@PathVariable Long id) {
        BorrowedBooksDTO borrowedBooks = borrowedBooksService.getBorrowedBooks(id);
        return ResponseEntity.ok(borrowedBooks);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<BorrowedBooksDTO> getBorrowedBooksByUser(@RequestBody User user) {
        BorrowedBooksDTO borrowedBooks = borrowedBooksService.getBorrowedBooksByUser(user);
        return ResponseEntity.ok(borrowedBooks);
    }
}
