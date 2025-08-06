package com.fullstack.todo.entity;

import com.fullstack.todo.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.lang.reflect.Member;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Task extends Audit{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long taskId;
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private TaskStatus taskStatus;

    @ManyToMany
    @JoinTable(
            name = "task_member",
            joinColumns = @JoinColumn(name = "taskId"),
            inverseJoinColumns = @JoinColumn(name = "memberId"))
    private List<TeamMember> teamMemberList;
}
