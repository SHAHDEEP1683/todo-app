package com.fullstack.todo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDataDto {

    public String message;
    public AdminResponseDto adminResponseDto;

}