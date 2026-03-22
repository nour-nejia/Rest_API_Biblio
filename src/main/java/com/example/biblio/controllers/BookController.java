package com.example.biblio.controllers;

import com.example.biblio.dto.BookDto;
import com.example.biblio.services.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // GET /
    @GetMapping
    public Page<BookDto> getBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return bookService.getAllBooks(page, size);
    }

    // GET /{id}
    @GetMapping("/{id}")
    public BookDto getBook(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    // POST /
    @PostMapping
    public BookDto createBook(@RequestBody BookDto dto) {
        return bookService.createBook(dto);
    }
}