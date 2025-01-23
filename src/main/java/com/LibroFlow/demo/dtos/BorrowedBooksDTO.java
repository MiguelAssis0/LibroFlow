package com.LibroFlow.demo.dtos;

import com.LibroFlow.demo.entities.Books;
import com.LibroFlow.demo.entities.BorrowedBooks;
import com.LibroFlow.demo.entities.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

public class BorrowedBooksDTO {
    private Long id;
    private Long user;
    private Long book;
    @NotBlank
    private String borrowDate;
    @NotBlank
    private String returnDate;

    public BorrowedBooksDTO() {}

    public BorrowedBooksDTO(Long id, User user, Books book, String borrowDate, String returnDate) {
        this.id = id;
        this.user = user.getId();
        this.book = book.getId();
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public BorrowedBooksDTO(BorrowedBooks borrowedBooks) {
        this.id = borrowedBooks.getId();
        this.user = borrowedBooks.getUser();
        this.book = borrowedBooks.getBook();
        this.borrowDate = borrowedBooks.getBorrowDate();
        this.returnDate = borrowedBooks.getReturnDate();
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
