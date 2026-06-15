package com.example.LibraryManagement.Exception;

import org.springframework.http.HttpStatus;

public class BookUnavailableException extends BaseException{

    public BookUnavailableException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
