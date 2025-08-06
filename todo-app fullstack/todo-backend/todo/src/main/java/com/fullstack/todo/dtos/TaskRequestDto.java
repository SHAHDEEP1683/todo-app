package com.fullstack.todo.dtos;

import com.fullstack.todo.entity.TeamMember;
import com.fullstack.todo.enums.TaskStatus;
import lombok.Data;

import java.util.List;

@Data
public class TaskRequestDto {

    private String title;
    private String description;
    private TaskStatus taskStatus;
    private List<Long> memberList;
}
