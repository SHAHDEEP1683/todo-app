package com.fullstack.todo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberTaskResponseDto {

    private String name;
    private List<TaskResponseDto> taskResponseDtoList;
}
