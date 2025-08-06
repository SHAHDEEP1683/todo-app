package com.fullstack.todo.mapper;

import com.fullstack.todo.dtos.*;
import com.fullstack.todo.entity.Admin;
import com.fullstack.todo.entity.AuthUsers;
import com.fullstack.todo.entity.Task;
import com.fullstack.todo.entity.TeamMember;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface GlobalMapper {

    Admin toAdmin(AdminRequestDto adminRequestDto);
    AdminResponseDto toAdminResponseDto(Admin admin);
    AuthUsers toAuthUser(AuthUsersRequestDto dto);

    TeamMember toTeamMember(MemberRequestDto memberRequestDto);
    MemberResponseDto toMemberResponseDto(TeamMember teamMember);

    Task toTask(TaskRequestDto taskRequestDto);
    TaskResponseDto toTaskResponseDto(Task task);

    default List<TaskResponseDto> mapTasks(List<Task> tasks) {
        if (tasks == null) return new ArrayList<>();
        return tasks.stream()
                .map(task -> new TaskResponseDto(task.getTitle(), task.getDescription(), task.getTaskStatus()))
                .toList();
    }

    @Mapping(source = "assignedTasks", target = "taskResponseDtoList")
    MemberTaskResponseDto toTaskMemberResponse(TeamMember teamMember);

}
