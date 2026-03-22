package com.example.biblio.dto;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BookDto {
    private Long id;
    private String isbn;
    private String title;
    private int stockDisponible;
    private String authorName;
}
