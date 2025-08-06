package com.fullstack.todo.dtos;

import lombok.Data;

import java.util.List;

@Data
public class MemberResponseDto {

    private String name;
    private String phoneNumber;
    private String email;
    private AuthUsersResponseDto authUsersResponseDto;
}