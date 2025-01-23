package com.LibroFlow.demo.entities;

import com.LibroFlow.demo.dtos.BorrowedBooksDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "borrowed_books")
public class BorrowedBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Long user;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Long book;
    private String borrowDate;
    private String returnDate;

    public BorrowedBooks() {}

    public BorrowedBooks(Long id, Long user, Long book, String borrowDate, String returnDate) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public BorrowedBooks(BorrowedBooksDTO dto) {
        this.id = dto.getId();
        this.user = dto.getUser();
        this.book = dto.getBook();
        this.borrowDate = dto.getBorrowDate();
        this.returnDate = dto.getReturnDate();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUser() { return user; }
    public void setUser(Long user) { this.user = user; }
    public Long getBook() { return book; }
    public void setBook(Long book) { this.book = book; }
    public String getBorrowDate() { return borrowDate; }
    public void setBorrowDate(String borrowDate) { this.borrowDate = borrowDate; }
    public String getReturnDate() { return returnDate; }
    public void setReturnDate(String returnDate) { this.returnDate = returnDate; }
}
