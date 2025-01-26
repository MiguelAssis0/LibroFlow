package com.LibroFlow.demo.dtos;

import com.LibroFlow.demo.entities.BorrowBooks;
import com.LibroFlow.demo.projections.BorrowBooksProjection;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class BorrowBooksDTO {
    private Long userId;
    private Long bookId;
    @NotNull
    private LocalDate returnDate;
    @NotNull
    private Boolean isReturned;

    public BorrowBooksDTO(Long id, Long bookId, LocalDate now, LocalDate localDate) {}

    public BorrowBooksDTO(BorrowBooks borrowBooks) {
        this.userId = borrowBooks.getId().getUser().getId();
        this.bookId = borrowBooks.getId().getBook().getId();
        this.returnDate = borrowBooks.getReturnDate();
        this.isReturned = borrowBooks.getIsReturned();
    }

    public BorrowBooksDTO(Long userId, Long bookId, LocalDate returnDate, Boolean isReturned) {
        this.userId = userId;
        this.bookId = bookId;
        this.returnDate = returnDate;
        this.isReturned = isReturned;
    }

    public BorrowBooksDTO(Long userId, Long bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public BorrowBooksDTO(BorrowBooksProjection borrowedBook) {
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

