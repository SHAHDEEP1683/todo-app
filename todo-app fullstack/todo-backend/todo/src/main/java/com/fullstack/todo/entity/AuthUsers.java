package com.fullstack.todo.entity;

import com.fullstack.todo.enums.RoleType;
import com.fullstack.todo.enums.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class AuthUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;


    @Column(unique = true)
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",message = "Invalid email format")
    private String email;

    @NotNull
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 20)
    private RoleType role;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

}
