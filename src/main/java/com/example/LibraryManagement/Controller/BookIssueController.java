package com.example.LibraryManagement.Controller;

import com.example.LibraryManagement.DTOs.BookIssueRequestDto;
import com.example.LibraryManagement.DTOs.Result;
import com.example.LibraryManagement.Model.BookIssue;
import com.example.LibraryManagement.Service.BookIssueService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.version}/bookIssue")
public class BookIssueController {

    private final BookIssueService bookIssueService;

    public BookIssueController(BookIssueService bookIssueService) {
        this.bookIssueService = bookIssueService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Result<BookIssue> createBookIssue(@Valid @RequestBody BookIssueRequestDto bookIssueRequestDto) {
        return bookIssueService.createBookIssue(bookIssueRequestDto);
    }

    @PostMapping("/return/{issueId}")
    public Result<BookIssue> returnBook(@PathVariable Long issueId) {
        return bookIssueService.returnBook(issueId);
    }

    @GetMapping
    public Result<List<BookIssue>> getAllBooks() {
        return bookIssueService.getAllBookIssue();
    }

    @GetMapping("/active")
    public Result<List<BookIssue>> getIssuedBooks() {
        return bookIssueService.getAllIssuedBooks();
    }

    @GetMapping("/member/{memberId}")
    public Result<List<BookIssue>> getAllMemberWhoIssueBooks(@PathVariable Long memberId) {
        return bookIssueService.getAllMemberWhoIssuedBooks(memberId);
    }
}