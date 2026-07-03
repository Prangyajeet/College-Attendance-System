package com.college.attendance.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.attendance.model.User;

public interface UserRepository 
        extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

    Optional<User> findByFacultyId(String facultyId);

    long countByRole(String role);

    List<User> findByRole(String role);

    // NEW - sorted faculty list
    List<User> findByRoleOrderByFacultyIdAsc(String role);
}