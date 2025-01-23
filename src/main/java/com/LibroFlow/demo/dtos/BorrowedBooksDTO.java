package com.LibroFlow.demo.dtos;

import com.LibroFlow.demo.entities.Books;
import com.LibroFlow.demo.entities.User;
import jakarta.persistence.*;

public class BorrowedBooksDTO {
    private Long id;
    private User user;
    private Books book;
    private String borrowDate;
    private String returnDate;

    public BorrowedBooksDTO() {}

    public BorrowedBooksDTO(Long id, User user, Books book, String borrowDate, String returnDate) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Books getBook() { return book; }
    public void setBook(Books book) { this.book = book; }
    public String getBorrowDate() { return borrowDate; }
    public void setBorrowDate(String borrowDate) { this.borrowDate = borrowDate; }
    public String getReturnDate() { return returnDate; }
    public void setReturnDate(String returnDate) { this.returnDate = returnDate; }
}
