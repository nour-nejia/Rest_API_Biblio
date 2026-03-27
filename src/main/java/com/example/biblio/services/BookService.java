package com.example.biblio.services;

import com.example.biblio.dto.BookDto;
import com.example.biblio.entities.Book;
import com.example.biblio.exception.BookException;
import com.example.biblio.mapper.BookMapper;
import com.example.biblio.repositories.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    // 📄 Liste paginée
    public Page<BookDto> getAllBooks(int page, int size) {
        return bookRepository.findAll(PageRequest.of(page, size))
                .map(bookMapper::toDto);
    }

    // 📘 Détail d’un livre
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookException("Book not found"));

        return bookMapper.toDto(book);
    }

    // ➕ Ajouter livre
    public BookDto createBook(BookDto dto) {
        Book book = bookMapper.toEntity(dto);
        return bookMapper.toDto(bookRepository.save(book));
    }
}