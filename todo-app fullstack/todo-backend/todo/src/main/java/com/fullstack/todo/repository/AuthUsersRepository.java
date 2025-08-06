package com.fullstack.todo.repository;

import com.fullstack.todo.entity.AuthUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthUsersRepository extends JpaRepository<AuthUsers, Long> {

    Optional<AuthUsers> findByEmail(String email);
}
