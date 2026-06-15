package com.example.LibraryManagement.Exception;

import org.springframework.http.HttpStatus;

public class IssueRecordNotFoundException extends BaseException{

    public IssueRecordNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
