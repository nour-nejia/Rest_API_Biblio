package com.example.biblio.repositories;

import com.example.biblio.entities.Borrowing;
import com.example.biblio.entities.BorrowingStatus;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {

    List<Borrowing> findByUserIdAndStatus(Long userId, BorrowingStatus status);
    List<Borrowing> findByStatusAndBorrowDateBefore(BorrowingStatus status, LocalDate date);

}
