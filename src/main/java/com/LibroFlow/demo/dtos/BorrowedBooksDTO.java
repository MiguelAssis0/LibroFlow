package com.LibroFlow.demo.dtos;

import com.LibroFlow.demo.entities.Books;
import com.LibroFlow.demo.entities.BorrowedBooks;
import com.LibroFlow.demo.entities.User;
import com.LibroFlow.demo.projections.BorrowedBooksProjection;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

public class BorrowedBooksDTO {
    private Long userId;
    private Long bookId;
    @NotBlank
    private String borrowDate;
    @NotBlank
    private String returnDate;

    public BorrowedBooksDTO() {}

    public BorrowedBooksDTO(BorrowedBooks borrowedBooks) {
        this.userId = borrowedBooks.getId().getUser().getId();
        this.bookId = borrowedBooks.getId().getBook().getId();
        this.borrowDate = borrowedBooks.getBorrowDate();
        this.returnDate = borrowedBooks.getReturnDate();
    }

    public BorrowedBooksDTO(Long userId, Long bookId, String borrowDate, String returnDate) {
        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;

    }

    public BorrowedBooksDTO(BorrowedBooksProjection borrowedBook) {
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}

