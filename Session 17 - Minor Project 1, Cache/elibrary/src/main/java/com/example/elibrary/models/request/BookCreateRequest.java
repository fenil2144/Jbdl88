package com.example.elibrary.models.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import com.example.elibrary.models.Author;
import com.example.elibrary.models.Book;
import com.example.elibrary.models.enums.Genre;

@Data
public class BookCreateRequest {

    @NotBlank
    private String title;

    @Positive
    private int cost;

    @NotNull
    private Genre genre;

    @NotNull
    private Author author;

    public Book toBook() {
        return Book.builder()
                .title(this.title)
                .cost(this.cost)
                .genre(this.genre)
                .author(this.author)
                .build();
    }
}
