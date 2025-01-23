package com.LibroFlow.demo.repository;

import com.LibroFlow.demo.entities.BorrowedBooks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowedBooksRepository extends JpaRepository<BorrowedBooks, Long> {
}
