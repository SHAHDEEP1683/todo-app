package com.fullstack.todo.repository;

import com.fullstack.todo.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    boolean existsByEmail(String email);
}
