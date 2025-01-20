package com.LibroFlow.demo.repository;

import com.LibroFlow.demo.entities.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Books, Long> {

    Books findByTitle(String title);
}
