package com.fullstack.todo.controller;

import com.fullstack.todo.dtos.AdminDataDto;
import com.fullstack.todo.dtos.AdminRequestDto;
import com.fullstack.todo.dtos.TaskDataDto;
import com.fullstack.todo.dtos.TaskRequestDto;
import com.fullstack.todo.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<AdminDataDto> registerAdmin(@RequestBody AdminRequestDto adminRequestDto){
        var service = adminService.createAdmin(adminRequestDto);
        return new ResponseEntity<>(new AdminDataDto(
                "Admin Register Successfully..", service),
                HttpStatus.CREATED);

    }

    @PostMapping("/approve-member/{userId}")
    public ResponseEntity<String> approveMember(@PathVariable("userId") Long userId) {
        String result = adminService.approveMember(userId);
        if ("Member is already active.".equals(result)) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/addTask")
    public ResponseEntity<TaskDataDto> createTask(@RequestBody TaskRequestDto taskRequestDto) {
        var response = adminService.createTask(taskRequestDto);
        return new ResponseEntity<>(new TaskDataDto(
                "Task is Created...",response),
                HttpStatus.CREATED);
    }
}
