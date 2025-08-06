package com.fullstack.todo.dtos;

import com.fullstack.todo.enums.RoleType;
import lombok.Data;

@Data
public class AuthUsersRequestDto {

    private String email;
    private String password;
    private RoleType role;
}
