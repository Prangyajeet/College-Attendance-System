package com.college.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.college.attendance.model.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    long countByStudentIdAndSubjectId(Long studentId, Long subjectId);

    long countByStudentIdAndSubjectIdAndStatus(
            Long studentId,
            Long subjectId,
            String status
    );
}
