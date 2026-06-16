package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.DTOs.BookIssueRequestDto;
import com.example.LibraryManagement.Model.BookIssue;
import com.example.LibraryManagement.Model.Member;

import java.util.List;

public interface BookIssueService {

    BookIssue createBookIssue(BookIssueRequestDto bookIssueRequestDto);

    List<BookIssue> getAllBookIssue();

    BookIssue returnBook(Long issueId);

    List<BookIssue> getAllIssuedBooks();

    List<BookIssue> getAllMemberWhoIssuedBooks(Long memberId);
}
