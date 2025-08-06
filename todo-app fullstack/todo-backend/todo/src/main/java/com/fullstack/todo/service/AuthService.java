package com.fullstack.todo.service;

import com.fullstack.todo.entity.LoginForm;
import com.fullstack.todo.dtos.LoginResponseDto;

public interface AuthService {

    LoginResponseDto authenticate(LoginForm loginForm);

}
