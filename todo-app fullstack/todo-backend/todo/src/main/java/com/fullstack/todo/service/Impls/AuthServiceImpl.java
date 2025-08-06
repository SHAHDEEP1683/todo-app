package com.fullstack.todo.service.Impls;

import com.fullstack.todo.JWT.JwtService;
import com.fullstack.todo.dtos.LoginResponseDto;
import com.fullstack.todo.entity.AuthUsers;
import com.fullstack.todo.entity.LoginForm;
import com.fullstack.todo.enums.RoleType;
import com.fullstack.todo.enums.UserStatus;
import com.fullstack.todo.repository.AuthUsersRepository;
import com.fullstack.todo.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthUsersRepository authUsersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponseDto authenticate(LoginForm loginForm) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginForm.getEmail(),
                        loginForm.getPassword()
                )
        );

        AuthUsers user = authUsersRepository.findByEmail(loginForm.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (user.getRole() != RoleType.ADMIN && user.getRole() != RoleType.SUPER_ADMIN && user.getRole() != RoleType.MEMBER) {
            throw new RuntimeException("Access denied: Not an authorized");
        }

        if (user.getUserStatus() != UserStatus.ACTIVE) {
            throw new RuntimeException("Account not active. Please wait for approval.");
        }


        User principal = (User) authentication.getPrincipal();
        String token = jwtService.generateToken(principal, user.getUserId(), user.getRole());

        log.info("Admin authenticated successfully: {}", user.getEmail());
        return new LoginResponseDto(token, user.getRole().name(), user.getUserId());
    }
}
