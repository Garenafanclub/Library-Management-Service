package com.example.LibraryManagement.Controller;

import com.example.LibraryManagement.DTOs.MemberRequestDto;
import com.example.LibraryManagement.Model.Member;
import com.example.LibraryManagement.Service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.version}/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping()
    public ResponseEntity<Member> createMember(@RequestBody MemberRequestDto requestDto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.createMember(requestDto));
    }

    @GetMapping()
    public ResponseEntity<List<Member>> getAllMember()
    {
        return ResponseEntity.ok(memberService.getAllMember());
    }
}
