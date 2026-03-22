package com.example.biblio.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BorrowRequestDto {
    private Long bookId;
    private Long userId;
}
