package com.fullstack.todo.service.Impls;

import com.fullstack.todo.dtos.AdminRequestDto;
import com.fullstack.todo.dtos.AdminResponseDto;
import com.fullstack.todo.dtos.TaskRequestDto;
import com.fullstack.todo.dtos.TaskResponseDto;
import com.fullstack.todo.entity.TeamMember;
import com.fullstack.todo.enums.RoleType;
import com.fullstack.todo.enums.UserStatus;
import com.fullstack.todo.enums.TaskStatus;
import com.fullstack.todo.mapper.GlobalMapper;
import com.fullstack.todo.repository.AdminRepository;
import com.fullstack.todo.repository.AuthUsersRepository;
import com.fullstack.todo.repository.MemberRepository;
import com.fullstack.todo.repository.TaskRepository;
import com.fullstack.todo.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final GlobalMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final AdminRepository adminRepository;
    private final MemberRepository memberRepository;
    private final TaskRepository taskRepository;
    private final AuthUsersRepository authUsersRepository;

    @Override
    public AdminResponseDto createAdmin(AdminRequestDto adminRequestDto) {
        if (adminRepository.existsByEmail(adminRequestDto.getEmail())) {
            log.warn("Admin with email {} already exists", adminRequestDto.getEmail());
            throw new RuntimeException("Admin already exists");
        }

        try {
            String encodedPassword = passwordEncoder.encode(adminRequestDto.getAuthUsersRequestDto().getPassword());
            log.info("Password Encoded : {}",encodedPassword);
            var authDto = adminRequestDto.getAuthUsersRequestDto();
            authDto.setPassword(encodedPassword);
            authDto.setRole(RoleType.ADMIN);

            var authUser = mapper.toAuthUser(authDto);
            authUser.setUserStatus(UserStatus.UNACTIVE);
            var savedAuthUser = authUsersRepository.save(authUser);
            log.info("AuthUser Saved : {}",authUser);

            var admin = mapper.toAdmin(adminRequestDto);
            admin.setAuthUsers(savedAuthUser);
            admin.setEmail(authDto.getEmail());
            log.info("AuthSet In Admin Tab");
            var savedAdmin = adminRepository.save(admin);
            log.info("Admin Saved");

            return mapper.toAdminResponseDto(savedAdmin);
        } catch (Exception e) {
            log.error("Error creating admin: {}", e.getMessage());
            throw new RuntimeException("Admin creation failed");
        }
    }

    @Override
    public String approveMember(Long userId) {
        var existingMember = memberRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Member not found with ID: " + userId));

        var authUser = existingMember.getAuthUsers();
        if (authUser.getUserStatus() == UserStatus.ACTIVE) {
            return "Member is already active.";
        }

        authUser.setUserStatus(UserStatus.ACTIVE);
        authUsersRepository.save(authUser);
        return "Member has been activated successfully.";
    }

    @Override
    public TaskResponseDto createTask(TaskRequestDto taskRequestDto) {
        var newTask = mapper.toTask(taskRequestDto);
        newTask.setTaskStatus(TaskStatus.IN_PROGRESS);
        List<Long> memberIds = taskRequestDto.getMemberList();
        List<TeamMember> teamMembers = memberIds.stream()
                .map(id -> memberRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Member Not Found")))
                                .collect(Collectors.toList());
        newTask.setTeamMemberList(teamMembers);
        var savedTask = taskRepository.save(newTask);
        log.info("Saved Task : {}",savedTask);
        return mapper.toTaskResponseDto(savedTask);
    }
}