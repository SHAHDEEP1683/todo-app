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
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long adminId;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @Pattern(regexp = "^[6-9]\\d{9}$",
            message = "Invalid phone number. It should be a 10-digit number starting with 6-9.")
    private String phoneNumber;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "parentId",
            referencedColumnName = "userId", nullable = false)
    private AuthUsers authUsers;


}
