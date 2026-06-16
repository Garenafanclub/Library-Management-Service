package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.DTOs.BookIssueRequestDto;
import com.example.LibraryManagement.DTOs.Result;
import com.example.LibraryManagement.Model.BookIssue;

import java.util.List;

public interface BookIssueService {

    Result<BookIssue> createBookIssue(BookIssueRequestDto bookIssueRequestDto);

    Result<List<BookIssue>> getAllBookIssue();

    Result<BookIssue> returnBook(Long issueId);

    Result<List<BookIssue>> getAllIssuedBooks();

    Result<List<BookIssue>> getAllMemberWhoIssuedBooks(Long memberId);
}