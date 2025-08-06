package com.fullstack.todo.repository;

import com.fullstack.todo.entity.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<TeamMember, Long> {

    boolean existsByEmail(String email);

    @Query("SELECT m FROM TeamMember m LEFT JOIN FETCH m.assignedTasks WHERE m.memberId = :memberId")
    Optional<TeamMember> findByIdWithTasks(@Param("memberId") Long memberId);

}
