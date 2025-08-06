package com.fullstack.todo.controller;

import com.fullstack.todo.dtos.LoginResponseDto;
import com.fullstack.todo.entity.LoginForm;
import com.fullstack.todo.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/admin")
    public ResponseEntity<LoginResponseDto> adminLogin(@RequestBody @Valid LoginForm loginForm) {
        LoginResponseDto response = authService.authenticate(loginForm);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/superadmin")
    public ResponseEntity<LoginResponseDto> superadminLogin(@RequestBody @Valid LoginForm loginForm) {
        LoginResponseDto response = authService.authenticate(loginForm);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/member")
    public ResponseEntity<LoginResponseDto> memberLogin(@RequestBody @Valid LoginForm loginForm) {
        LoginResponseDto response = authService.authenticate(loginForm);
        return ResponseEntity.ok(response);
    }
}
