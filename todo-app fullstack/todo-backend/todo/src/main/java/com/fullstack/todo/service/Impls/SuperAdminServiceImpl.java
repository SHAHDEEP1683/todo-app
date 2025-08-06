package com.fullstack.todo.service.Impls;

import com.fullstack.todo.enums.UserStatus;
import com.fullstack.todo.repository.AdminRepository;
import com.fullstack.todo.repository.AuthUsersRepository;
import com.fullstack.todo.service.SuperAdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SuperAdminServiceImpl implements SuperAdminService {

    private final AdminRepository adminRepository;
    private final AuthUsersRepository authUsersRepository;

    @Override
    public String approveAdmin(Long userId) {
        var existingAdmin = adminRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Admin not found with ID: " + userId));

        var authUser = existingAdmin.getAuthUsers();
        if (authUser.getUserStatus() == UserStatus.ACTIVE) {
            return "Admin is already active.";
        }

        authUser.setUserStatus(UserStatus.ACTIVE);
        authUsersRepository.save(authUser);
        return "Admin has been activated successfully.";
    }


}
