package com.LibroFlow.demo.service;

import com.LibroFlow.demo.dtos.BooksDTO;
import com.LibroFlow.demo.entities.Books;
import com.LibroFlow.demo.repository.BooksRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BooksRepository bookRepository;

    public void createBook(BooksDTO dto){
        Books book = new Books(dto.getTitle(), dto.getAuthor(), dto.getGenre(), dto.getDescription(), dto.getQuantity(), dto.getAvailable());
        bookRepository.save(book);
    }

    public Page<BooksDTO> findAllBooks(Pageable pageable){
        Page<Books> books = bookRepository.findAll(pageable);
        return books.map(book -> new BooksDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getDescription(), book.getQuantity(), book.getAvailable()));
    }

    public BooksDTO findByTitle(String title){
        Books book = bookRepository.findByTitle(title);
        return new BooksDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getDescription(), book.getQuantity(), book.getAvailable());
    }

    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }

    public BooksDTO updateBook(BooksDTO dto){
        Books book = bookRepository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
        if (dto.getTitle() != null) book.setTitle(dto.getTitle());
        if (dto.getAuthor() != null) book.setAuthor(dto.getAuthor());
        if(dto.getAvailable() != null) book.setAvailable(dto.getAvailable());
        if(dto.getGenre() != null) book.setGenre(dto.getGenre());
        if(dto.getDescription() != null) book.setDescription(dto.getDescription());
        if(dto.getQuantity() != book.getQuantity() && dto.getQuantity() > 0) book.setQuantity(dto.getQuantity());

        bookRepository.save(book);
        return new BooksDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getDescription(), book.getQuantity(), book.getAvailable());
    }
}
