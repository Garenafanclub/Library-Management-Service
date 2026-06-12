package com.example.LibraryManagement.Mapper;

import com.example.LibraryManagement.DTOs.MemberRequestDto;
import com.example.LibraryManagement.Model.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    // DTO --> ENTITY...
    Member toEntity(MemberRequestDto requestDto);
}
