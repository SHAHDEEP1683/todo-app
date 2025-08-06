package com.fullstack.todo.service.Impls;

import com.fullstack.todo.dtos.*;
import com.fullstack.todo.enums.RoleType;
import com.fullstack.todo.enums.UserStatus;
import com.fullstack.todo.mapper.GlobalMapper;
import com.fullstack.todo.repository.AuthUsersRepository;
import com.fullstack.todo.repository.MemberRepository;
import com.fullstack.todo.repository.TaskRepository;
import com.fullstack.todo.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final GlobalMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final TaskRepository taskRepository;
    private final AuthUsersRepository authUsersRepository;

    @Override
    public MemberResponseDto createMember(MemberRequestDto memberRequestDto) {
        if (memberRepository.existsByEmail(memberRequestDto.getEmail())) {
            log.warn("Member with email {} already exists", memberRequestDto.getEmail());
            throw new RuntimeException("Member already exists");
        }

        try {
            String encodedPassword = passwordEncoder.encode(memberRequestDto.getAuthUsersRequestDto().getPassword());
            log.info("Password Encoded : {}",encodedPassword);
            var authDto = memberRequestDto.getAuthUsersRequestDto();
            authDto.setPassword(encodedPassword);
            authDto.setRole(RoleType.MEMBER);

            var authUser = mapper.toAuthUser(authDto);
            authUser.setUserStatus(UserStatus.UNACTIVE);
            var savedAuthUser = authUsersRepository.save(authUser);
            log.info("AuthUser Saved : {}",authUser);

            var member = mapper.toTeamMember(memberRequestDto);
            member.setAuthUsers(savedAuthUser);
            member.setEmail(authDto.getEmail());
            var savedMember = memberRepository.save(member);
            log.info("Member Saved");

            return mapper.toMemberResponseDto(savedMember);
        } catch (Exception e) {
            log.error("Error creating member: {}", e.getMessage());
            throw new RuntimeException("Member creation failed");
        }
    }

    @Override
    public MemberTaskResponseDto displayMember(Long memberId) {
        var existingMember = memberRepository.findByIdWithTasks(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        return mapper.toTaskMemberResponse(existingMember);
    }
}