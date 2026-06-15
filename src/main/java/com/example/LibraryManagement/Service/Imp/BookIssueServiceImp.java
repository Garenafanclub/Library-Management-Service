package com.example.LibraryManagement.Service.Imp;

import com.example.LibraryManagement.DTOs.BookIssueRequestDto;
import com.example.LibraryManagement.Exception.BookUnavailableException;
import com.example.LibraryManagement.Exception.MaximumBookLimitExceededException;
import com.example.LibraryManagement.Exception.MemberNotFoundException;
import com.example.LibraryManagement.Mapper.BookIssueMapper;
import com.example.LibraryManagement.Model.Book;
import com.example.LibraryManagement.Model.BookIssue;
import com.example.LibraryManagement.Model.Member;
import com.example.LibraryManagement.Repository.BookIssueRepo;
import com.example.LibraryManagement.Repository.BookRepo;
import com.example.LibraryManagement.Repository.MemberRepo;
import com.example.LibraryManagement.Service.BookIssueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookIssueServiceImp implements BookIssueService {
    private final BookIssueMapper bookIssueMapper;
    private final BookIssueRepo bookIssueRepo;
    private final BookRepo bookRepo;
    private final MemberRepo memberRepo;

    public BookIssueServiceImp(BookIssueMapper bookIssueMapper, BookIssueRepo bookIssueRepo, BookRepo bookRepo, MemberRepo memberRepo) {
        this.bookIssueMapper = bookIssueMapper;
        this.bookIssueRepo = bookIssueRepo;
        this.bookRepo = bookRepo;
        this.memberRepo = memberRepo;
    }

    @Override
    public BookIssue createBookIssue(BookIssueRequestDto bookIssueRequestDto) {

        Book book = bookRepo.findById(bookIssueRequestDto.getBookId())
                .orElseThrow(() -> new BookUnavailableException("Book not found"));
        Member member = memberRepo.findById(bookIssueRequestDto.getMemberId())
                .orElseThrow(() -> new MemberNotFoundException("Member not found"));

        if(book.getAvailableCopies() <= 0)
        {
            throw new BookUnavailableException("Available copies must be greater than 0");
        }

        int activeIssue = bookIssueRepo.countByMemberIdAndStatus(member.getId(),"ISSUED");
        if(activeIssue >= 3)
        {
            throw new MaximumBookLimitExceededException("Member has already issued 3 books.");
        }

        BookIssue bookIssue = bookIssueMapper.toEntity(bookIssueRequestDto);
        bookIssue.setStatus("ISSUED");

        return bookIssueRepo.save(bookIssue);
    }

    @Override
    public BookIssue returnBook(Long issueId) {
        return null;
    }

    @Override
    public List<BookIssue> getAllBookIssue() {
        return bookIssueRepo.findAll();
    }


}
