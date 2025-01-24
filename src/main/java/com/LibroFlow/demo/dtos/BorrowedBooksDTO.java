package com.LibroFlow.demo.dtos;

import com.LibroFlow.demo.entities.Books;
import com.LibroFlow.demo.entities.BorrowedBooks;
import com.LibroFlow.demo.entities.User;
import com.LibroFlow.demo.projections.BorrowedBooksProjection;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class BorrowedBooksDTO {
    private Long userId;
    private Long bookId;
    @NotNull
    private LocalDate returnDate;
    @NotNull
    private Boolean isReturned;

    public BorrowedBooksDTO() {}

    public BorrowedBooksDTO(BorrowedBooks borrowedBooks) {
        this.userId = borrowedBooks.getId().getUser().getId();
        this.bookId = borrowedBooks.getId().getBook().getId();
        this.returnDate = borrowedBooks.getReturnDate();
        this.isReturned = borrowedBooks.getIsReturned();
    }

    public BorrowedBooksDTO(Long userId, Long bookId, LocalDate returnDate, Boolean isReturned) {
        this.userId = userId;
        this.bookId = bookId;
        this.returnDate = returnDate;
        this.isReturned = isReturned;
    }

    public BorrowedBooksDTO(Long userId, Long bookId) {
        this.userId = userId;
        this.bookId = bookId;
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

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Boolean getIsReturned() {
        return isReturned;
    }

    public void setIsReturned(Boolean isReturned) {
        this.isReturned = isReturned;
    }
}

