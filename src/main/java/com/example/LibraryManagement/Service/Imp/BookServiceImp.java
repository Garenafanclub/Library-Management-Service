package com.example.LibraryManagement.Service.Imp;

import com.example.LibraryManagement.DTOs.BookRequestDto;
import com.example.LibraryManagement.Mapper.BookMapper;
import com.example.LibraryManagement.Model.Book;
import com.example.LibraryManagement.Repository.BookRepo;
import com.example.LibraryManagement.Service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImp implements BookService {

    private final BookRepo bookRepo;
    private final BookMapper bookMapper;

    public BookServiceImp(BookRepo bookRepo, BookMapper bookMapper) {
        this.bookRepo = bookRepo;
        this.bookMapper = bookMapper;
    }

    @Override
    public Book createBook(BookRequestDto bookRequestDto) {
        Book book = bookMapper.toEntity(bookRequestDto);
        return bookRepo.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }
}
