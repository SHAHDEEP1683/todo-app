package com.fullstack.todo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDataDto {

    public String message;
    public MemberResponseDto memberResponseDto;
}
