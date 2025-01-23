package com.LibroFlow.demo.entities;

import com.LibroFlow.demo.dtos.BorrowedBooksDTO;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "borrowed_books")
public class BorrowedBooks {
    @EmbeddedId
    private BorrowedBooksPK id;
    private String borrowDate;
    private String returnDate;
    private Boolean isReturned;

    public BorrowedBooks() {}

    public BorrowedBooks(BorrowedBooksDTO borrowedBooksDTO, User user, Books books) {
        this.id = new BorrowedBooksPK(user, books);
        this.borrowDate = borrowedBooksDTO.getBorrowDate();
        this.returnDate = borrowedBooksDTO.getReturnDate();
        this.isReturned = borrowedBooksDTO.getIsReturned();
    }

    public BorrowedBooksPK getId() {
        return id;
    }

    public void setId(BorrowedBooksPK id) {
        this.id = id;
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

    public Boolean getIsReturned() {
        return isReturned;
    }

    public void setIsReturned(Boolean isReturned) {
        this.isReturned = isReturned;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BorrowedBooks that = (BorrowedBooks) o;
        return Objects.equals(id, that.id) && Objects.equals(borrowDate, that.borrowDate) && Objects.equals(returnDate, that.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, borrowDate, returnDate);
    }
}
