package com.fullstack.todo.service;

import com.fullstack.todo.dtos.AdminRequestDto;
import com.fullstack.todo.dtos.AdminResponseDto;
import com.fullstack.todo.dtos.TaskRequestDto;
import com.fullstack.todo.dtos.TaskResponseDto;

public interface AdminService {

    AdminResponseDto createAdmin(AdminRequestDto adminRequestDto);
    String approveMember(Long usesId);
    TaskResponseDto createTask(TaskRequestDto taskRequestDto);
}
