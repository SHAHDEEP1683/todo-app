package com.fullstack.todo.security;

import com.fullstack.todo.entity.AuthUsers;
import com.fullstack.todo.repository.AuthUsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthUsersRepository authUsersRepository;

    public CustomUserDetailsService(AuthUsersRepository authUsersRepository) {
        this.authUsersRepository = authUsersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AuthUsers> user = authUsersRepository.findByEmail(email);
        log.info("LoadUserByUserName : {}",user);
        return User.builder()
            .username(user.get().getEmail())
            .password(user.get().getPassword())
            .roles(user.get().getRole().name())
            .build();
    }
}
