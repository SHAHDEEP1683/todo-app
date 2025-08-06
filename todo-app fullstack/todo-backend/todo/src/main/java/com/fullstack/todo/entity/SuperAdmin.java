package com.fullstack.todo.entity;

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
public class SuperAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long superAdminId;

    @NotNull
    private String name;

    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Invalid phone number. It should be a 10-digit number starting with 6-9."
    )
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private AuthUsers authUsers;
}
