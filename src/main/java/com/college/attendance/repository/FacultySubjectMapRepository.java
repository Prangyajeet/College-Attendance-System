package com.college.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

import com.college.attendance.model.FacultySubjectMap;

public interface FacultySubjectMapRepository
        extends JpaRepository<FacultySubjectMap, String> {

    List<FacultySubjectMap> findByFaculty_FacultyId(String facultyId);

    // Delete mappings when faculty is deleted
    @Transactional
    @Modifying
    void deleteByFaculty_FacultyId(String facultyId);
}