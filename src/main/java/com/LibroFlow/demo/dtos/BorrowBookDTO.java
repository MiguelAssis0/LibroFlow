package com.LibroFlow.demo.dtos;

import com.LibroFlow.demo.entities.BorrowBook;
import com.LibroFlow.demo.projections.BorrowBookProjection;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class BorrowBookDTO {
    private Long userId;
    private Long bookId;
    @NotNull
    private LocalDate returnDate;
    @NotNull
    @JsonIgnore
    private Boolean isReturned = false;

    public BorrowBookDTO() {}

    public BorrowBookDTO(Long id, Long bookId, LocalDate now, LocalDate localDate) {}

    public BorrowBookDTO(BorrowBook borrowBook) {
        this.userId = borrowBook.getId().getUser().getId();
        this.bookId = borrowBook.getId().getBook().getId();
        this.returnDate = borrowBook.getReturnDate();
        this.isReturned = borrowBook.getIsReturned();
    }

    public BorrowBookDTO(Long userId, Long bookId, LocalDate returnDate) {
        this.userId = userId;
        this.bookId = bookId;
        this.returnDate = returnDate;
    }

    public BorrowBookDTO(Long userId, Long bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

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

