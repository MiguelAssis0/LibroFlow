package com.LibroFlow.demo.dtos;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public class ReturnBookDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String title;

    public ReturnBookDTO() {}
    public ReturnBookDTO(String username, String title) {
        this.username = username;
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
