package com.fullstack.todo.controller;

import com.fullstack.todo.dtos.MemberDataDto;
import com.fullstack.todo.dtos.MemberRequestDto;
import com.fullstack.todo.dtos.MemberTaskResponseDto;
import com.fullstack.todo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<MemberDataDto> registerMember(@RequestBody MemberRequestDto memberRequestDto) {
        var service = memberService.createMember(memberRequestDto);
        return new ResponseEntity<>(new MemberDataDto(
                "Member Register Successfully..", service), HttpStatus.CREATED);

    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberTaskResponseDto> displayMember(@PathVariable("memberId") Long memberId) {
        var responseDto = memberService.displayMember(memberId);
        return ResponseEntity.ok(responseDto);
    }
}
