package com.LibroFlow.demo.dtos;

import com.LibroFlow.demo.enums.Genre;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class BooksDTO {
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
    private Boolean available;

    public BooksDTO() {}

    public BooksDTO(Long id,String title, String author, Genre genre, String description, Integer quantity, Boolean available) {
        setId(id);
        setTitle(title);
        setAuthor(author);
        setGenre(genre);
        setDescription(description);
        setQuantity(quantity);
        setAvailable(available);
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
    public void setQuantity(int quantity) { this.quantity = BooksDTO.this.quantity; }

    public Boolean getAvailable() { return available; }
    public void setAvailable(Boolean available) { this.available = available; }
}
