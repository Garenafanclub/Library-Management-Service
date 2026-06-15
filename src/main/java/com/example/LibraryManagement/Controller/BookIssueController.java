package com.example.LibraryManagement.Controller;

import com.example.LibraryManagement.DTOs.BookIssueRequestDto;
import com.example.LibraryManagement.Model.BookIssue;
import com.example.LibraryManagement.Service.BookIssueService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BookIssue> createBookIssue(@Valid @RequestBody BookIssueRequestDto bookIssueRequestDto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookIssueService.createBookIssue(bookIssueRequestDto));
    }

    @PostMapping("/return/{issueId}")
    public ResponseEntity<BookIssue> returnBook(@PathVariable Long issueId)
    {
        BookIssue returnedIssue = bookIssueService.returnBook(issueId);
        return new ResponseEntity<>(returnedIssue, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BookIssue>> getAllBooks()
    {
        return ResponseEntity.ok(bookIssueService.getAllBookIssue());
    }
}
