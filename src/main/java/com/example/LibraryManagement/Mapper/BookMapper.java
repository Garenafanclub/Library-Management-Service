package com.example.LibraryManagement.Mapper;

import com.example.LibraryManagement.DTOs.BookRequestDto;
import com.example.LibraryManagement.Model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    // DTO - ENTITY...
    Book toEntity(BookRequestDto bookRequestDto);
}
