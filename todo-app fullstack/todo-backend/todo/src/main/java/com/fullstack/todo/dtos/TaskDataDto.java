package com.fullstack.todo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskDataDto{

    private String message;
    private TaskResponseDto taskResponseDto;

}