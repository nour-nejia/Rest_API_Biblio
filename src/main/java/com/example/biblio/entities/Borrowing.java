package com.example.biblio.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Borrowing extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate borrowDate;

    private LocalDate returnDate;

    @Enumerated(EnumType.STRING)
    private BorrowingStatus status;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private Long userId;

}