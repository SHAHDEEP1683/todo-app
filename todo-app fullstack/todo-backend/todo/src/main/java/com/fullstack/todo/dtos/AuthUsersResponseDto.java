package com.fullstack.todo.dtos;

import com.fullstack.todo.enums.RoleType;
import com.fullstack.todo.enums.UserStatus;
import lombok.Data;

@Data
public class AuthUsersResponseDto {

    private Long userId;
    private String email;
    private String password;
    private UserStatus userStatus;
    private RoleType role;
}