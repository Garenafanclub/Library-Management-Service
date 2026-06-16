package com.example.LibraryManagement.Service.Imp;

import com.example.LibraryManagement.DTOs.BookIssueRequestDto;
import com.example.LibraryManagement.DTOs.Result;
import com.example.LibraryManagement.Exception.*;
import com.example.LibraryManagement.Mapper.BookIssueMapper;
import com.example.LibraryManagement.Model.Book;
import com.example.LibraryManagement.Model.BookIssue;
import com.example.LibraryManagement.Model.Member;
import com.example.LibraryManagement.Repository.BookIssueRepo;
import com.example.LibraryManagement.Repository.BookRepo;
import com.example.LibraryManagement.Repository.MemberRepo;
import com.example.LibraryManagement.Service.BookIssueService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    @Transactional
    public Result<BookIssue> createBookIssue(BookIssueRequestDto bookIssueRequestDto) {

        Book book = bookRepo.findById(bookIssueRequestDto.getBookId())
                .orElseThrow(() -> new BookUnavailableException("Book not found"));
        Member member = memberRepo.findById(bookIssueRequestDto.getMemberId())
                .orElseThrow(() -> new MemberNotFoundException("Member not found"));

        if (book.getAvailableCopies() <= 0) {
            throw new BookUnavailableException("Available copies must be greater than 0");
        }

        int activeIssue = bookIssueRepo.countByMemberIdAndStatus(member.getId(),"ISSUED");
        if (activeIssue >= 3) {
            throw new MaximumBookLimitExceededException("Member has already issued 3 books.");
        }

        BookIssue bookIssue = bookIssueMapper.toEntity(bookIssueRequestDto);
        bookIssue.setBook(book);
        bookIssue.setMember(member);
        bookIssue.setIssueDate(LocalDateTime.now());
        bookIssue.setStatus("ISSUED");

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepo.save(book);

        BookIssue savedIssue = bookIssueRepo.save(bookIssue);

        return new Result<>("201", "Book successfully issued", savedIssue);
    }

    @Override
    @Transactional
    public Result<BookIssue> returnBook(Long issueId) {
        BookIssue bookIssue = bookIssueRepo.findById(issueId)
                .orElseThrow(()-> new IssueRecordNotFoundException("Issued Book not found"));

        if ("RETURNED".equalsIgnoreCase(bookIssue.getStatus())) {
            throw new RuntimeException("Can not return an already returned book");
        }

        bookIssue.setStatus("RETURNED");
        bookIssue.setReturnDate(LocalDateTime.now());

        Book book = bookIssue.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);

        bookRepo.save(book);

        BookIssue savedIssue = bookIssueRepo.save(bookIssue);

        return new Result<>("200", "Book successfully returned", savedIssue);
    }

    @Override
    public Result<List<BookIssue>> getAllBookIssue() {
        List<BookIssue> allIssues = bookIssueRepo.findAll();

        if (allIssues.isEmpty()) {
            throw new IssueRecordNotFoundException("No book issue history exists in the system.");
        }

        return new Result<>("200", "All book issues fetched successfully", allIssues);
    }

    @Override
    public Result<List<BookIssue>> getAllIssuedBooks() {
        List<BookIssue> activeIssues = bookIssueRepo.findActiveIssue();

        if (activeIssues.isEmpty()) {
            throw new IssueRecordNotFoundException("There are currently no active book issues in the library.");
        }

        return new Result<>("200", "Active book issues fetched successfully", activeIssues);
    }

    @Override
    public Result<List<BookIssue>> getAllMemberWhoIssuedBooks(Long memberId) {
        if (!memberRepo.existsById(memberId)) {
            throw new MemberNotFoundException("Member not found with id: " + memberId);
        }

        List<BookIssue> memberIssues = bookIssueRepo.findMemberIssuedTheBooks(memberId);

        if (memberIssues.isEmpty()) {
            throw new IssueRecordNotFoundException("No active book issues found for member id: " + memberId);
        }

        return new Result<>("200", "Member's active issues fetched successfully", memberIssues);
    }
}