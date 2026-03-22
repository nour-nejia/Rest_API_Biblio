package com.example.biblio.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class BorrowingDto {
    private Long id;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private String status;
    private Long bookId;
    private Long userId;
}
