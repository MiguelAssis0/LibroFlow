package com.LibroFlow.demo.dtos;

import com.LibroFlow.demo.projections.BorrowedBooksProjection;
import jakarta.validation.constraints.NotNull;


public class BorrowedBooksProjectionDTO {
    private String userName;
    private String bookTitle;
    private String borrowDate;
    private String returnDate;
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

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowedDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public Boolean getIsReturned() {
        return isReturned;
    }

    public void setIsReturned(Boolean isReturned) {
        this.isReturned = isReturned;
    }
}
