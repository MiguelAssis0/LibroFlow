package com.LibroFlow.demo.service;

import com.LibroFlow.demo.dtos.BooksDTO;
import com.LibroFlow.demo.entities.Books;
import com.LibroFlow.demo.infra.exceptions.EventNotFoundException;
import com.LibroFlow.demo.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BookService {
    @Autowired
    private BooksRepository bookRepository;

    public void createBook(BooksDTO dto){
        Books book = new Books(dto);
        bookRepository.save(book);
    }

    public List<BooksDTO> findAllBooks() {
        List<Books> books = bookRepository.findAll();
        if(books.isEmpty()) throw new EventNotFoundException("Nenhum livro encontrado");
        return books.stream().map(BooksDTO::new).toList();
    }

    public BooksDTO findByTitle(String title){
        Books book = bookRepository.findByTitle(title);
        if(book == null) throw new EventNotFoundException("Livro não encontrado");
        return new BooksDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getDescription(), book.getQuantity(), book.getAvailable());
    }

    public void deleteBook(Long id){
        Books book = bookRepository.findById(id).orElseThrow(EventNotFoundException::new);
        bookRepository.delete(book);
    }

    public BooksDTO updateBook(Long id, BooksDTO dto){
        Books book = bookRepository.findById(id).orElseThrow(EventNotFoundException::new);
        if (dto.getTitle() != null) book.setTitle(dto.getTitle());
        if (dto.getAuthor() != null) book.setAuthor(dto.getAuthor());
        if(dto.getAvailable() != null) book.setAvailable(dto.getAvailable());
        if(dto.getGenre() != null) book.setGenre(dto.getGenre());
        if(dto.getDescription() != null) book.setDescription(dto.getDescription());
        if(!Objects.equals(dto.getQuantity(), book.getQuantity()) && dto.getQuantity() > 0) book.setQuantity(dto.getQuantity());

        bookRepository.save(book);
        return new BooksDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getDescription(), book.getQuantity(), book.getAvailable());
    }
}
