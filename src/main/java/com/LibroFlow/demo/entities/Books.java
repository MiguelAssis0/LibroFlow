package com.LibroFlow.demo.entities;


import com.LibroFlow.demo.enums.Genre;
import jakarta.persistence.*;

@Table(name = "books")
@Entity
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private String description;
    private double price;
    private Boolean available;

    public Books(){}
    public Books(String title, String author, Genre genre, String description, double price, Boolean available) {
        setTitle(title);
        setAuthor(author);
        setGenre(genre);
        setDescription(description);
        setPrice(price);
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

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public Boolean getAvailable() { return available; }
    public void setAvailable(Boolean available) { this.available = available; }

}
