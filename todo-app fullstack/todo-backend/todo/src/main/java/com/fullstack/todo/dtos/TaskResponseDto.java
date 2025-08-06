package com.fullstack.todo.dtos;

import com.fullstack.todo.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDto {

    private String title;
    private String description;
    private TaskStatus taskStatus;
}
