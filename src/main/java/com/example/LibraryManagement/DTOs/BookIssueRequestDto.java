package com.example.LibraryManagement.DTOs;

import com.example.LibraryManagement.Model.Book;
import com.example.LibraryManagement.Model.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookIssueRequestDto {

    private Long bookId;
    private Long memberId;
}
