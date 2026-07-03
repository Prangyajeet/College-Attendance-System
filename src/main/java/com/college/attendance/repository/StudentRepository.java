package com.college.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.college.attendance.model.Student;

public interface StudentRepository extends JpaRepository<Student, String> {

    // Filter students by semester + department
    List<Student> findBySemester_SemIdAndDepartment_DeptId(
            String semId,
            String deptId
    );

    // ⭐ NEW - filter by department only
    List<Student> findByDepartment_DeptId(String deptId);

//////////////////////////////////////////////////
//🔥 LOGIN METHOD (UPDATED)
//////////////////////////////////////////////////

Student findByEmail(String email);
}