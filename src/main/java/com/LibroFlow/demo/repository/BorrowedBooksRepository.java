package com.LibroFlow.demo.repository;

import com.LibroFlow.demo.entities.BorrowedBooks;
import com.LibroFlow.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BorrowedBooksRepository extends JpaRepository<BorrowedBooks, Long> {

    Optional<BorrowedBooks> findById(Long id);

    @Query("SELECT b FROM BorrowedBooks b WHERE b.user = ?1")
    BorrowedBooks findByUser(User user);
}
