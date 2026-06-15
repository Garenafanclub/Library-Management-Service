package com.example.LibraryManagement.Exception;

import org.springframework.http.HttpStatus;

public class BookNotFoundException extends BaseException {

    public BookNotFoundException(String message, HttpStatus status) {
        super(message, status);
    }
}
