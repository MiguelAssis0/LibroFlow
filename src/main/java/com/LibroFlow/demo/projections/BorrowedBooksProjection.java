package com.LibroFlow.demo.projections;

import java.time.LocalDate;

public interface BorrowedBooksProjection {
    String getUserName();
    String getBookTitle();
    LocalDate getBorrowDate();
    LocalDate getReturnDate();
    Boolean getIsReturned();
}
