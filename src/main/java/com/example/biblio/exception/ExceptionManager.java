package com.example.biblio.exception;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.*;

@RestControllerAdvice
public class ExceptionManager {

    //  NOT FOUND(404)
    @ExceptionHandler(BookException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(BookException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    //  BAD REQUEST (400)
    @ExceptionHandler({
            StockException.class,
            BorrowLimitException.class,
            DataIntegrityViolationException.class
    })
    public ResponseEntity<Map<String, String>> handleBadRequest(RuntimeException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    //  FALLBACK (toutes les autres erreurs)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneral(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Internal server error");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}