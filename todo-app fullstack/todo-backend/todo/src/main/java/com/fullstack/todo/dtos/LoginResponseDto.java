package com.fullstack.todo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class LoginResponseDto {
    private String token;
    private String role;
    private Long userId;
}