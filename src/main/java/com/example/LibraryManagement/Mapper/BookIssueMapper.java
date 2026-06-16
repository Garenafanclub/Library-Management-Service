package com.example.LibraryManagement.Mapper;

import com.example.LibraryManagement.DTOs.BookIssueRequestDto;
import com.example.LibraryManagement.Model.BookIssue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookIssueMapper {

    // DTO - ENTITY..
    @Mapping(target = "book", ignore = true)
    @Mapping(target = "member", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "issueDate", ignore = true)
    @Mapping(target = "returnDate", ignore = true)
    @Mapping(target = "status", ignore = true)
    BookIssue toEntity(BookIssueRequestDto bookIssueRequestDto);
}
