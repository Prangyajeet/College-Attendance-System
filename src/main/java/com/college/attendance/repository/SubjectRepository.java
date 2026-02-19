package com.college.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.college.attendance.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
