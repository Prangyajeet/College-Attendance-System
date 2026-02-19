package com.college.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.college.attendance.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
