package com.example.biblio.services;

import com.example.biblio.dto.BookDto;
import com.example.biblio.dto.BorrowingDto;
import com.example.biblio.entities.*;
import com.example.biblio.exception.StockException;
import com.example.biblio.mapper.BorrowingMapper;
import com.example.biblio.repositories.BookRepository;
import com.example.biblio.repositories.BorrowingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BorrowingServiceTest {
@Mock
    private BookRepository bookRepository;
@Mock
    private BorrowingRepository borrowingRepository;
@Mock
    private BorrowingMapper borrowingMapper;
@InjectMocks
    private BorrowingService borrowingService;
@Test
    void shouldSuccessfullyBorrowBook() {
        Author author = new Author();
        author.setId(1L);
        author.setName("Miguel De Cervantes");
        Category category = new Category();
        category.setId(1L);
        category.setLabel("Philosophie");
        Set<Category> categories = new HashSet<>();
        categories.add(category);
        Book book = new Book(1L,"909","Don Quichotte",5,author,categories);
        Borrowing savedBorrowing = new Borrowing();
        savedBorrowing.setBook(book);
        savedBorrowing.setUserId(24L);
        // trouver le livre
        when(bookRepository.findById(1L))
                .thenReturn(Optional.of(book));
        // aucun emprunt en cours
        when(borrowingRepository.findByUserIdAndStatus(24L, BorrowingStatus.ONGOING))
                .thenReturn(List.of());
        when(borrowingRepository.save(any(Borrowing.class)))
                .thenReturn(savedBorrowing);


        BorrowingDto dto = new BorrowingDto();
        dto.setUserId(24L);
        when(borrowingMapper.toDto(any(Borrowing.class)))
                .thenReturn(dto);
        BorrowingDto result = borrowingService.processBorrowing(1L, 24L);

        assertNotNull(result);
        assertEquals(24L, result.getUserId());
        assertEquals(4, book.getStockDisponible()); // stock décrémenté
        verify(bookRepository).findById(1L);
        verify(borrowingRepository).save(any(Borrowing.class));
    }
    @Test
    void shouldThrowStockException() {
        Author author = new Author();
        author.setId(1L);
        author.setName("Miguel De Cervantes");
        Category category = new Category();
        category.setId(1L);
        category.setLabel("Philosophie");
        Set<Category> categories = new HashSet<>();
        categories.add(category);
        Book book = new Book(1L, "909", "Don Quichotte", 0, author, categories);
        //livre trouvé
        when(bookRepository.findById(1L))
                .thenReturn(Optional.of(book));
        // Vérifier que l’exception est levée
        assertThrows(StockException.class, () -> {
            borrowingService.processBorrowing(1L, 24L);
        });

        verify(borrowingRepository, never()).save(any(Borrowing.class));
    }

}
