package com.college.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;

import com.college.attendance.model.FacultySubject;

public interface FacultySubjectRepository extends JpaRepository<FacultySubject, Long> {

    @Transactional
    @Modifying
    void deleteByFaculty_FacultyId(String facultyId);
}