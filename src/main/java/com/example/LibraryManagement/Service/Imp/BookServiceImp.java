package com.example.LibraryManagement.Service.Imp;

import com.example.LibraryManagement.DTOs.BookRequestDto;
import com.example.LibraryManagement.Exception.BookUnavailableException;
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
        if (bookRepo.existsByIsbn(bookRequestDto.getIsbn())) {
            throw new RuntimeException("A book with this ISBN already exists in the system.");
        }
        Book book = bookMapper.toEntity(bookRequestDto);
        return bookRepo.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = bookRepo.findAll();

        if (books.isEmpty()) {
            throw new BookUnavailableException("No Books are currently available in the library inventory.");
        }

        return books;
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepo.findById(id)
                .orElseThrow(()-> new BookUnavailableException("Book is not present with id:" + id));
    }

    @Override
    public Void deleteBook(Long id) {
        if(bookRepo.existsById(id))
        {
            bookRepo.deleteById(id);
        }
        else{
            throw new BookUnavailableException("The book you are trying to delete is not present in the inventory");
        }
        return null;
    }
}
