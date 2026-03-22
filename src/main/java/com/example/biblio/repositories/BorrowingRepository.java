package com.example.biblio.repositories;

import com.example.biblio.entities.Borrowing;
import com.example.biblio.entities.BorrowingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {

    List<Borrowing> findByStatus(BorrowingStatus status);
}
