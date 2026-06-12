package com.example.LibraryManagement.Controller;

import com.example.LibraryManagement.DTOs.BookRequestDto;
import com.example.LibraryManagement.Model.Book;
import com.example.LibraryManagement.Service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.version}/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Book> createBooks(@RequestBody BookRequestDto bookRequestDto)
    {
       return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(bookRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks()
    {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

}
