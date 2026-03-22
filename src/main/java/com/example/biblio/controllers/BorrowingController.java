package com.example.biblio.controllers;

import com.example.biblio.dto.BorrowingDto;
import com.example.biblio.dto.BorrowRequestDto;
import com.example.biblio.services.BorrowingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/borrowings")
public class BorrowingController {

    @Autowired
    private BorrowingService borrowingService;

    // POST /checkout
    @PostMapping("/checkout")
    public BorrowingDto checkout(@RequestBody BorrowRequestDto request) {
        return borrowingService.processBorrowing(
                request.getBookId(),
                request.getUserId()
        );
    }

    // POST /return/{id}
    @PostMapping("/return/{id}")
    public BorrowingDto returnBook(@PathVariable Long id) {
        return borrowingService.returnBook(id);
    }

    // GET /user/{userId}
    @GetMapping("/user/{userId}")
    public List<BorrowingDto> getUserBorrowings(@PathVariable Long userId) {
        return borrowingService.getUserBorrowings(userId);
    }
}