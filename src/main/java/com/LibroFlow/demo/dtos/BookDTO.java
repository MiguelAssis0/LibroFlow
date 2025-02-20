package com.LibroFlow.demo.dtos;

import com.LibroFlow.demo.entities.Book;
import com.LibroFlow.demo.enums.Genre;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;


public class BookDTO implements Serializable {
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @NotBlank
    private String description;
    @Positive
    private Integer quantity;
    @NotNull
    private Boolean available;

    public BookDTO() {}

    public BookDTO(Long id, String title, String author, Genre genre, String description, Integer quantity, Boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.description = description;
        this.quantity = quantity;
        this.available = available;
    }

    public BookDTO(String title, String author, Genre genre, String description, Integer quantity, Boolean available) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.description = description;
        this.quantity = quantity;
        this.available = available;
    }

    public BookDTO(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.genre = book.getGenre();
        this.description = book.getDescription();
        this.quantity = book.getQuantity();
        this.available = book.getAvailable();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public Genre getGenre() { return genre; }
    public void setGenre(Genre genre) { this.genre = genre; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public Boolean getAvailable() { return available; }
    public void setAvailable(Boolean available) { this.available = available; }
}
