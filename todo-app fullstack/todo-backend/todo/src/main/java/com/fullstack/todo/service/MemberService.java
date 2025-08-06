package com.fullstack.todo.service;

import com.fullstack.todo.dtos.MemberRequestDto;
import com.fullstack.todo.dtos.MemberResponseDto;
import com.fullstack.todo.dtos.MemberTaskResponseDto;

public interface MemberService {

    MemberResponseDto createMember(MemberRequestDto memberRequestDto);
    MemberTaskResponseDto displayMember(Long memberId);

}
