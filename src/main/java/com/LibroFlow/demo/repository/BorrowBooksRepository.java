package com.LibroFlow.demo.repository;

import com.LibroFlow.demo.entities.BorrowBooks;
import com.LibroFlow.demo.entities.BorrowBooksPK;
import com.LibroFlow.demo.projections.BorrowBooksProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BorrowBooksRepository extends JpaRepository<BorrowBooks, Long> {
    @Query(nativeQuery = true, value = """
    SELECT
        u.username AS user_name,
        b.title AS book_title,
        bb.borrow_date,
        bb.return_date,
        bb.is_returned
    FROM
        borrowed_books bb
    JOIN
        users u ON bb.user_id = u.id
    JOIN
        books b ON bb.book_id = b.id
    WHERE
        bb.is_returned = false;
""")
    List<BorrowBooksProjection> findAllBorrowedBooks();

    @Query(nativeQuery = true, value = """
    SELECT
        *
    FROM
        borrowed_books
    WHERE
        user_id = :userId AND
        book_id = :bookId
    """)
    BorrowBooks findByUserAndBook(Long userId, Long bookId);

    @Query(nativeQuery = true, value = """
    SELECT
        u.username AS user_name,
        b.title AS book_title,
        bb.borrow_date,
        bb.return_date,
        bb.is_returned
    FROM
        borrowed_books bb
    JOIN
        users u ON bb.user_id = u.id
    JOIN
        books b ON bb.book_id = b.id
    WHERE
        bb.is_returned = true;
""")
    List<BorrowBooksProjection> findAllReturnedBooks();
    BorrowBooks findById(BorrowBooksPK borrowBooksPK);
}
