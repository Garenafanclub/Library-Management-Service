package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.DTOs.MemberRequestDto;
import com.example.LibraryManagement.Model.Member;

import java.util.List;

public interface MemberService {
    Member createMember(MemberRequestDto memberRequestDto);

    List<Member> getAllMember();

    Member getMemberById(Long id);

    Void deleteMember(Long id);
}
