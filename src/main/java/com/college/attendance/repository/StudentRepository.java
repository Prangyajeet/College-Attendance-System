package com.college.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.college.attendance.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
