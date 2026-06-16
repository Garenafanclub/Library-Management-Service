package com.example.LibraryManagement.Service.Imp;

import com.example.LibraryManagement.DTOs.MemberRequestDto;
import com.example.LibraryManagement.Exception.MemberNotFoundException;
import com.example.LibraryManagement.Mapper.MemberMapper;
import com.example.LibraryManagement.Model.Member;
import com.example.LibraryManagement.Repository.MemberRepo;
import com.example.LibraryManagement.Service.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImp implements MemberService {

    private final MemberRepo memberRepo;
    private final MemberMapper memberMapper;

    public MemberServiceImp(MemberRepo memberRepo, MemberMapper memberMapper) {
        this.memberRepo = memberRepo;
        this.memberMapper = memberMapper;
    }

    @Override
    public Member createMember(MemberRequestDto memberRequestDto) {

        if (memberRepo.existsByMemberCode(memberRequestDto.getMemberCode())) {
            throw new RuntimeException("A member with this member code already exists in the system.");
        }

        Member member = memberMapper.toEntity(memberRequestDto);
        return memberRepo.save(member);
    }

    @Override
    public List<Member> getAllMember() {
        List<Member> members = memberRepo.findAll();

        if (members.isEmpty()) {
            throw new MemberNotFoundException("No members are currently registered in the library system.");
        }
        return members;
    }

    @Override
    public Member getMemberById(Long id) {
        return memberRepo.findById(id)
                .orElseThrow(()-> new MemberNotFoundException("Member is not present with id:" + id));
    }

    @Override
    public Void deleteMember(Long id) {
        if(memberRepo.existsById(id))
        {
            memberRepo.deleteById(id);
        }
        else {
            throw new MemberNotFoundException("Member you are trying to delete is not present with id" + id);
        }
        return null;
    }
}
