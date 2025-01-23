package com.LibroFlow.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "borrowed_books")
public class BorrowedBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Books book;
    private String borrowDate;
    private String returnDate;
}
