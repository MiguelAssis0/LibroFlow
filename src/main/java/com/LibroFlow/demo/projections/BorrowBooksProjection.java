package com.LibroFlow.demo.projections;

import java.time.LocalDate;

public interface BorrowBooksProjection {
    String getUserName();
    String getBookTitle();
    LocalDate getBorrowDate();
    LocalDate getReturnDate();
    Boolean getIsReturned();
}
