package com.example.LibraryManagement.Exception;

import org.springframework.http.HttpStatus;

public class MaximumBookLimitExceededException extends BaseException{

    public MaximumBookLimitExceededException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
