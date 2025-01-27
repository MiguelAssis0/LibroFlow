package com.LibroFlow.demo.service;

import com.LibroFlow.demo.dtos.BorrowBookDTO;
import com.LibroFlow.demo.dtos.BorrowBookProjectionDTO;
import com.LibroFlow.demo.dtos.ReturnBookDTO;
import com.LibroFlow.demo.entities.Book;
import com.LibroFlow.demo.entities.BorrowBook;
import com.LibroFlow.demo.entities.User;
import com.LibroFlow.demo.infra.exceptions.EventFullException;
import com.LibroFlow.demo.infra.exceptions.EventNotFoundException;
import com.LibroFlow.demo.projections.BorrowBookProjection;
import com.LibroFlow.demo.repository.BookRepository;
import com.LibroFlow.demo.repository.BorrowBookRepository;
import com.LibroFlow.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowBooksService {
    @Autowired
    private BorrowBookRepository borrowBookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CacheService cacheService;

    public BorrowBookDTO createBorrowedBooks(BorrowBookDTO borrowBookDTO) {

        User user = userRepository.findById(borrowBookDTO.getUserId())
                .orElseThrow(() -> new EventNotFoundException("Usuário não encontrado"));

        Book book = bookRepository.findById(borrowBookDTO.getBookId())
                .orElseThrow(() -> new EventNotFoundException("Livro não encontrado"));

        if (book.getQuantity() < 1) throw new EventFullException("Livro indisponivel");

        BorrowBook borrowBook = new BorrowBook(borrowBookDTO, user, book);
        borrowBookRepository.save(borrowBook);
        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);
        cacheService.evictAllCacheValues("book");
        cacheService.evictAllCacheValues("allBorrowedBooks");
        return new BorrowBookDTO(borrowBook);

    }

    @Cacheable("allBorrowedBooks")
    public List<BorrowBookProjectionDTO> getAllBorrowedBooks() {
        List<BorrowBookProjection> borrowBooks = borrowBookRepository.findAllBorrowedBooks();
        if(borrowBooks.isEmpty()) throw new EventNotFoundException("Nenhum empréstimo encontrado");
        return borrowBooks.stream().map(BorrowBookProjectionDTO::new).toList();
    }

    @Cacheable("allReturnedBooks")
    public List<BorrowBookProjectionDTO> getAllReturnedBooks() {
        List<BorrowBookProjection> returnedBooks = borrowBookRepository.findAllReturnedBooks();
        if(returnedBooks.isEmpty()) throw new EventNotFoundException("Não há empréstimos retornados");
        return returnedBooks.stream().map(BorrowBookProjectionDTO::new).toList();
    }

    public void returnBook(ReturnBookDTO dto){
        User user = userRepository.findByUsername(dto.getUsername());
        if(user == null) throw new EventNotFoundException("Usuário nao encontrado");
        Book book = bookRepository.findByTitle(dto.getTitle());
        if(book == null) throw new EventNotFoundException("Livro nao encontrado");
        BorrowBook borrowBook = borrowBookRepository.findByUserAndBook(user.getId(), book.getId());
        if(borrowBook == null) throw new EventNotFoundException("Empréstimo não existe");
        book.setQuantity(book.getQuantity() + 1);
        borrowBook.setIsReturned(true);
        borrowBookRepository.save(borrowBook);
        cacheService.evictAllCacheValues("allBorrowedBooks");
        bookRepository.save(book);
        cacheService.evictAllCacheValues("book");
    }
}