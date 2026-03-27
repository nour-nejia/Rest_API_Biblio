package com.example.biblio.scheduler;
import com.example.biblio.entities.Borrowing;
import com.example.biblio.entities.BorrowingStatus;
import com.example.biblio.repositories.BorrowingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;
@Component
public class BorrowingScheduler {
    @Autowired
    private BorrowingRepository borrowingRepository;
    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void checkOverdueBorrowings() {
        LocalDate limitDate = LocalDate.now().minusDays(14);
        // Cherche les emprunts ONGOING empruntés il y a > 14 jours
        List<Borrowing> overdueBorrowings =
                borrowingRepository.findByStatusAndBorrowDateBefore(
                        BorrowingStatus.ONGOING, limitDate
                );
        for (Borrowing borrowing : overdueBorrowings) {
            borrowing.setStatus(BorrowingStatus.OVERDUE);
        }
        borrowingRepository.saveAll(overdueBorrowings);
    }
}


