package com.example.biblio.services;

import com.example.biblio.dto.BorrowingDto;
import com.example.biblio.entities.*;
import com.example.biblio.exception.*;
import com.example.biblio.mapper.BorrowingMapper;
import com.example.biblio.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowingService {

    @Autowired
    private BorrowingRepository borrowingRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowingMapper borrowingMapper;

    // 🔥 Création emprunt (TP)
    @Transactional
    public BorrowingDto processBorrowing(Long bookId, Long userId) {

        // Vérifier existence livre
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookException("Book not found"));

        // Vérifier stock
        if (book.getStockDisponible() <= 0) {
            throw new StockException("Stock épuisé");
        }

        // Vérifier limite utilisateur (max 3)
        List<Borrowing> activeBorrowings =
                borrowingRepository.findByUserIdAndStatus(userId, BorrowingStatus.ONGOING);

        if (activeBorrowings.size() >= 3) {
            throw new BorrowLimitException("Limite atteinte");
        }

        // Décrémenter stock
        book.setStockDisponible(book.getStockDisponible() - 1);

        // Créer emprunt
        Borrowing borrowing = new Borrowing();
        borrowing.setBook(book);
        borrowing.setUserId(userId);
        borrowing.setBorrowDate(LocalDate.now());
        borrowing.setStatus(BorrowingStatus.ONGOING);

        return borrowingMapper.toDto(borrowingRepository.save(borrowing));
    }

    // 🔁 Retour livre
    @Transactional
    public BorrowingDto returnBook(Long id) {

        Borrowing borrowing = borrowingRepository.findById(id)
                .orElseThrow(() -> new BookException("Borrowing not found"));

        borrowing.setStatus(BorrowingStatus.RETURNED);
        borrowing.setReturnDate(LocalDate.now());

        // remettre stock
        Book book = borrowing.getBook();
        book.setStockDisponible(book.getStockDisponible() + 1);

        return borrowingMapper.toDto(borrowingRepository.save(borrowing));
    }

    // 📄 Liste emprunts utilisateur
    public List<BorrowingDto> getUserBorrowings(Long userId) {

        return borrowingRepository
                .findByUserIdAndStatus(userId, BorrowingStatus.ONGOING)
                .stream()
                .map(borrowingMapper::toDto)
                .toList();
    }
}