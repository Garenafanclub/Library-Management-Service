package com.example.LibraryManagement.DTOs;

import com.example.LibraryManagement.Model.Book;
import com.example.LibraryManagement.Model.Member;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookIssueRequestDto {

    private Long id;

    private LocalDateTime issueDate;
    private LocalDateTime returnDate;
    private String status;

    private Book bookId;

    private Member memberId;
}
