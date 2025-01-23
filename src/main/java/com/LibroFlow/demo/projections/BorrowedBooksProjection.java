package com.LibroFlow.demo.projections;

import java.time.LocalDate;

public interface BorrowedBooksProjection {
    String getUserName();
    String getBookTitle();
    String getBorrowDate();
    String getReturnDate();
}
