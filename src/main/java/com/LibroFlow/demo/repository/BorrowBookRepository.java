package com.LibroFlow.demo.repository;

import com.LibroFlow.demo.entities.BorrowBook;
import com.LibroFlow.demo.entities.BorrowBookPK;
import com.LibroFlow.demo.projections.BorrowBookProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BorrowBookRepository extends JpaRepository<BorrowBook, Long> {
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
        book b ON bb.book_id = b.id
    WHERE
        bb.is_returned = false;
""")
    List<BorrowBookProjection> findAllBorrowedBooks();

    @Query(nativeQuery = true, value = """
    SELECT
        *
    FROM
        borrowed_books
    WHERE
        user_id = :userId AND
        book_id = :bookId
    """)
    BorrowBook findByUserAndBook(Long userId, Long bookId);

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
        book b ON bb.book_id = b.id
    WHERE
        bb.is_returned = true;
""")
    List<BorrowBookProjection> findAllReturnedBooks();
    BorrowBook findById(BorrowBookPK borrowBookPK);
}
