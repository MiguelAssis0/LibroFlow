package com.LibroFlow.demo.dtos;

public class ReturnBookDTO {
    private String username;
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
