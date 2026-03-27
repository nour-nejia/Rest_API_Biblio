package com.example.biblio.exception;

public class BorrowLimitException extends RuntimeException {
    public BorrowLimitException(String message) {
        super(message);
    }
}
