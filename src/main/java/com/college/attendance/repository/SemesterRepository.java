package com.college.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.college.attendance.model.Semester;

public interface SemesterRepository 
        extends JpaRepository<Semester, String> {
}