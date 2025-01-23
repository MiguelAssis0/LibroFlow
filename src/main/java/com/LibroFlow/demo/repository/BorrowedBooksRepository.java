package com.LibroFlow.demo.repository;

import com.LibroFlow.demo.entities.BorrowedBooks;
import com.LibroFlow.demo.projections.BorrowedBooksProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BorrowedBooksRepository extends JpaRepository<BorrowedBooks, Long> {
    @Query(nativeQuery = true, value = """
    SELECT
        u.username AS user_name,
        b.title AS book_title,
        bb.borrow_date,
        bb.return_date
    FROM
        borrowed_books bb
    JOIN
        users u ON bb.user_id = u.id
    JOIN
        books b ON bb.book_id = b.id;
""")
    Page<BorrowedBooksProjection> findAllBorrowedBooks(Pageable pageable);


}
