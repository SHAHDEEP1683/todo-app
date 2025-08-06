package com.fullstack.todo.dtos;

import lombok.Data;

@Data
public class AdminRequestDto {

    private String name;
    private String phoneNumber;
    private String email;
    private AuthUsersRequestDto authUsersRequestDto;
}
