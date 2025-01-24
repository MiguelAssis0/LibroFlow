package com.LibroFlow.demo.dtos;

import com.LibroFlow.demo.projections.BorrowedBooksProjection;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


public class BorrowedBooksProjectionDTO {
    private String userName;
    private String bookTitle;
    private LocalDate borrowDate;

    private LocalDate returnDate;
    private Boolean isReturned;

    public BorrowedBooksProjectionDTO(BorrowedBooksProjection borrowedBook) {
        this.userName = borrowedBook.getUserName();
        this.bookTitle = borrowedBook.getBookTitle();
        this.borrowDate = borrowedBook.getBorrowDate();
        this.returnDate = borrowedBook.getReturnDate();
        this.isReturned = borrowedBook.getIsReturned();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowedDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
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
