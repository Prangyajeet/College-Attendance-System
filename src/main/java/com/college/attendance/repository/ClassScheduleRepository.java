package com.college.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

import com.college.attendance.model.ClassSchedule;

public interface ClassScheduleRepository extends JpaRepository<ClassSchedule, Long> {

    List<ClassSchedule> findByFaculty_FacultyId(String facultyId);

    // Delete schedules when faculty is deleted
    @Transactional
    @Modifying
    void deleteByFaculty_FacultyId(String facultyId);
}