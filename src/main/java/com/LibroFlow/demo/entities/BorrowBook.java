package com.LibroFlow.demo.entities;

import com.LibroFlow.demo.dtos.BorrowBookDTO;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "borrowed_books")
public class BorrowBook {
    @EmbeddedId
    private BorrowBookPK id;
    @Column(name = "borrow_date", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDate borrowDate = LocalDate.now();
    @Column(name = "return_date", nullable = false)
    private LocalDate returnDate;
    private Boolean isReturned;

    public BorrowBook() {}

    public BorrowBook(BorrowBookPK borrowBookPK, LocalDate borrowDate, LocalDate now, User user, Book book) {}

    public BorrowBook(BorrowBookDTO borrowBookDTO, User user, Book book) {
        this.id = new BorrowBookPK(user, book);
        this.returnDate = borrowBookDTO.getReturnDate();
        this.isReturned = borrowBookDTO.getIsReturned();
    }

    public BorrowBookPK getId() {
        return id;
    }

    public void setId(BorrowBookPK id) {
        this.id = id;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BorrowBook that = (BorrowBook) o;
        return Objects.equals(id, that.id) && Objects.equals(borrowDate, that.borrowDate) && Objects.equals(returnDate, that.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, borrowDate, returnDate);
    }
}
