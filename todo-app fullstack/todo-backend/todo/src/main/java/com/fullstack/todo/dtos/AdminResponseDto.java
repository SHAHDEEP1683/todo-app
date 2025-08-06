package com.fullstack.todo.dtos;

import lombok.Data;

@Data
public class AdminResponseDto {

    private String name;
    private String phoneNumber;
    private String email;
    private AuthUsersResponseDto authUsersResponseDto;
}
