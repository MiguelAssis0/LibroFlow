package com.LibroFlow.demo.controllers;

import com.LibroFlow.demo.dtos.BooksDTO;
import com.LibroFlow.demo.service.BookService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/books")
@SecurityRequirement(name = "bearer-key")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Void> createBook(@RequestBody @Valid BooksDTO dto) {
        bookService.createBook(dto);
        URI address = URI.create("/books/" + dto.getId());
        return ResponseEntity.created(address).build();
    }

    @GetMapping
    public ResponseEntity<List<BooksDTO>> findAll() {
        List<BooksDTO> booksPage = bookService.findAllBooks();
        return ResponseEntity.ok(booksPage);
    }


    @GetMapping("/{title}")
    public ResponseEntity<BooksDTO> FindByTitle(@PathVariable String title) {
        BooksDTO book = bookService.findByTitle(title);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BooksDTO> updateBook(@PathVariable Long id, @RequestBody BooksDTO dto) {
        BooksDTO response = bookService.updateBook(id, dto);
        return ResponseEntity.ok(response);
    }
}
