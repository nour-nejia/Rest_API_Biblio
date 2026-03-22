package com.example.biblio.exception;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import java.util.*;

@RestControllerAdvice
public class ExceptionManager{

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleException(RuntimeException ex) {

        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}