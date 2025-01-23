package com.LibroFlow.demo.dtos;

import com.LibroFlow.demo.entities.Books;
import com.LibroFlow.demo.entities.BorrowedBooks;
import com.LibroFlow.demo.entities.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

public class BorrowedBooksDTO {
    private Long id;
    private User user;
    private Books book;
    @NotBlank
    private String borrowDate;
    @NotBlank
    private String returnDate;

    public BorrowedBooksDTO() {}

    public BorrowedBooksDTO(Long id, User user, Books book, String borrowDate, String returnDate) {
        this.id = id;
        this.user = user;
        this.book = book;
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

    public BorrowedBooksDTO(BorrowedBooks borrowedBooks, User user, Books book) {
        this.id = borrowedBooks.getId();
        this.user = user;
        this.book = book;
        this.borrowDate = borrowedBooks.getBorrowDate();
        this.returnDate = borrowedBooks.getReturnDate();
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
