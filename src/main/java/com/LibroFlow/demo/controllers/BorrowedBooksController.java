package com.LibroFlow.demo.controllers;

import com.LibroFlow.demo.dtos.BorrowedBooksDTO;
import com.LibroFlow.demo.service.BorrowedBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
