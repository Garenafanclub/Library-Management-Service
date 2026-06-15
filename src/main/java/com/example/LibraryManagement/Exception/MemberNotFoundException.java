package com.example.LibraryManagement.Exception;

import org.springframework.http.HttpStatus;

public class MemberNotFoundException extends BaseException{

    public MemberNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
