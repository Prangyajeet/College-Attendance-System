package com.college.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query; // ✅ ADDED

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.college.attendance.model.Attendance;
import com.college.attendance.dto.DeanReportDTO; // ✅ ADDED

public interface AttendanceRepository 
        extends JpaRepository<Attendance, Long> {

    // Prevent duplicate attendance
    Optional<Attendance> findByStudentStudentIdAndSubjectSubjectIdAndDate(
            String studentId,
            String subjectId,
            LocalDate date
    );

    // Count total classes
    long countByStudentStudentIdAndSubjectSubjectId(
            String studentId,
            String subjectId
    );

    // Count present classes
    long countByStudentStudentIdAndSubjectSubjectIdAndStatus(
            String studentId,
            String subjectId,
            String status
    );

    // Attendance by date
    List<Attendance> findByDate(LocalDate date);

    // Attendance by subject
    List<Attendance> findBySubjectSubjectId(String subjectId);

    // Filter by faculty
    List<Attendance> findByDateAndFacultyFacultyId(
            LocalDate date,
            String facultyId
    );

    // Delete attendance when faculty is removed
    @Transactional
    @Modifying
    void deleteByFacultyFacultyId(String facultyId);

    // Delete attendance when student is removed
    @Transactional
    @Modifying
    void deleteByStudentStudentId(String studentId);
    
    List<Attendance> findByStudent_StudentId(String studentId);

    //////////////////////////////////////////////////
    // ✅ DEAN REPORT QUERY (NEW)
    //////////////////////////////////////////////////
    
    @Query("""
    SELECT new com.college.attendance.dto.DeanReportDTO(
        s.name,
        d.departmentName,
        sem.semesterNumber,
        sub.subjectName,
        (SUM(CASE WHEN a.status = 'PRESENT' THEN 1 ELSE 0 END) * 100.0 / COUNT(a))
    )
    FROM Attendance a
    JOIN a.student s
    JOIN s.department d
    JOIN s.semester sem
    JOIN a.subject sub
    GROUP BY s.name, d.departmentName, sem.semesterNumber, sub.subjectName
    """)
    List<DeanReportDTO> getDeanReport();
}