package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.DTOs.BookRequestDto;
import com.example.LibraryManagement.Model.Book;

import java.util.List;

public interface BookService {
    Book createBook(BookRequestDto bookRequestDto);

    List<Book> getAllBooks();
}
