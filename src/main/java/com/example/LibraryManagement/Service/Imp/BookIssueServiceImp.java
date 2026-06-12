package com.example.LibraryManagement.Service.Imp;

import com.example.LibraryManagement.DTOs.BookIssueRequestDto;
import com.example.LibraryManagement.Mapper.BookIssueMapper;
import com.example.LibraryManagement.Model.BookIssue;
import com.example.LibraryManagement.Repository.BookIssueRepo;
import com.example.LibraryManagement.Service.BookIssueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookIssueServiceImp implements BookIssueService {
    private final BookIssueMapper bookIssueMapper;
    private final BookIssueRepo bookIssueRepo;

    public BookIssueServiceImp(BookIssueMapper bookIssueMapper, BookIssueRepo bookIssueRepo) {
        this.bookIssueMapper = bookIssueMapper;
        this.bookIssueRepo = bookIssueRepo;
    }

    @Override
    public BookIssue createBookIssue(BookIssueRequestDto bookIssueRequestDto) {
        BookIssue bookIssue = bookIssueMapper.toEntity(bookIssueRequestDto);
        return bookIssueRepo.save(bookIssue);
    }

    @Override
    public List<BookIssue> getAllBookIssue() {
        return bookIssueRepo.findAll();
    }
}
