package com.example.LibraryManagement.Mapper;

import com.example.LibraryManagement.DTOs.BookIssueRequestDto;
import com.example.LibraryManagement.Model.BookIssue;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookIssueMapper {

    // DTO - ENTITY..
    BookIssue toEntity(BookIssueRequestDto bookIssueRequestDto);
}
