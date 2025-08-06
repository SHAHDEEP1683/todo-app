package com.fullstack.todo.controller;

import com.fullstack.todo.service.SuperAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/superadmin")
@RequiredArgsConstructor
public class SuperAdminController {

    private final SuperAdminService service;

    @PostMapping("/approve-admin/{userId}")
    public ResponseEntity<String> approveAdmin(@PathVariable("userId") Long userId) {
        String result = service.approveAdmin(userId);
        if ("Admin is already active.".equals(result)) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.ok(result);
    }

}