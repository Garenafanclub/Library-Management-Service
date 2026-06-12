package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.DTOs.BookIssueRequestDto;
import com.example.LibraryManagement.Model.BookIssue;

import java.util.List;

public interface BookIssueService {

    BookIssue createBookIssue(BookIssueRequestDto bookIssueRequestDto);

    List<BookIssue> getAllBookIssue();
}
