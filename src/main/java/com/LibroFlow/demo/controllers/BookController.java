package com.LibroFlow.demo.controllers;

import com.LibroFlow.demo.dtos.BookDTO;
import com.LibroFlow.demo.service.BookService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/book")
@SecurityRequirement(name = "bearer-key")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Void> createBook(@RequestBody @Valid BookDTO dto) {
        bookService.createBook(dto);
        URI address = URI.create("/book/" + dto.getId());
        return ResponseEntity.created(address).build();
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> findAll() {
        List<BookDTO> bookPage = bookService.findAllBooks();
        return ResponseEntity.ok(bookPage);
    }


    @GetMapping("/{title}")
    public ResponseEntity<BookDTO> FindByTitle(@PathVariable String title) {
        BookDTO book = bookService.findByTitle(title);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO dto) {
        BookDTO response = bookService.updateBook(id, dto);
        return ResponseEntity.ok(response);
    }
}
