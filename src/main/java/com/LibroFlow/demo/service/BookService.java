package com.LibroFlow.demo.service;

import com.LibroFlow.demo.dtos.BookDTO;
import com.LibroFlow.demo.entities.Book;
import com.LibroFlow.demo.infra.exceptions.EventNotFoundException;
import com.LibroFlow.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @CachePut(value = "book")
    public void createBook(BookDTO dto){
        Book book = new Book(dto);
        bookRepository.save(book);
    }

    @Cacheable(value = "book")
    public List<BookDTO> findAllBooks() {
        List<Book> books = bookRepository.findAll();
        if(books.isEmpty()) throw new EventNotFoundException("Nenhum livro encontrado");
        return books.stream().map(BookDTO::new).toList();
    }

    public BookDTO findByTitle(String title){
        Book book = bookRepository.findByTitle(title);
        if(book == null) throw new EventNotFoundException("Livro não encontrado");
        return new BookDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getDescription(), book.getQuantity(), book.getAvailable());
    }

    @CachePut(value = "book")
    public void deleteBook(Long id){
        Book book = bookRepository.findById(id).orElseThrow(EventNotFoundException::new);
        bookRepository.delete(book);
    }

    @CachePut(value = "book")
    public BookDTO updateBook(Long id, BookDTO dto){
        Book book = bookRepository.findById(id).orElseThrow(EventNotFoundException::new);
        if (dto.getTitle() != null) book.setTitle(dto.getTitle());
        if (dto.getAuthor() != null) book.setAuthor(dto.getAuthor());
        if(dto.getAvailable() != null) book.setAvailable(dto.getAvailable());
        if(dto.getGenre() != null) book.setGenre(dto.getGenre());
        if(dto.getDescription() != null) book.setDescription(dto.getDescription());
        if(!Objects.equals(dto.getQuantity(), book.getQuantity()) && dto.getQuantity() > 0) book.setQuantity(dto.getQuantity());

        bookRepository.save(book);
        return new BookDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getDescription(), book.getQuantity(), book.getAvailable());
    }
}
