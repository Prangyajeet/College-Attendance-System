package com.college.attendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.college.attendance.model.*;
import com.college.attendance.repository.*;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    //////////////////////////////////////////////////////
    // MARK ATTENDANCE
    //////////////////////////////////////////////////////

    public Attendance markAttendance(Attendance attendance, String facultyId) {

        String subjectId = attendance.getSubject().getSubjectId();
        String studentId = attendance.getStudent().getStudentId();

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        User faculty = userRepository.findById(facultyId)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));

        LocalDate today = LocalDate.now();

        // Prevent duplicate attendance
        Optional<Attendance> existing = attendanceRepository
                .findByStudentStudentIdAndSubjectSubjectIdAndDate(
                        studentId, subjectId, today);

        if (existing.isPresent()) {
            return existing.get();
        }

        // Create new attendance object
        Attendance newAttendance = new Attendance();
        newAttendance.setStudent(student);
        newAttendance.setSubject(subject);
        newAttendance.setFaculty(faculty);
        newAttendance.setStatus(attendance.getStatus());
        newAttendance.setDate(today);

        return attendanceRepository.save(newAttendance);
    }

    //////////////////////////////////////////////////////
    // GET ALL ATTENDANCE  ⭐ (USED BY FRONTEND)
    //////////////////////////////////////////////////////

    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    //////////////////////////////////////////////////////
    // GET ATTENDANCE BY DATE (FACULTY FILTER)
    //////////////////////////////////////////////////////

    public List<Attendance> getAttendanceByDate(String date, String facultyId) {

        LocalDate localDate = LocalDate.parse(date);

        return attendanceRepository
                .findByDateAndFacultyFacultyId(localDate, facultyId);
    }

    //////////////////////////////////////////////////////
    // GET ATTENDANCE BY SUBJECT (REPORT)
    //////////////////////////////////////////////////////

    public List<Attendance> getAttendanceReport(String subjectId) {

        return attendanceRepository.findBySubjectSubjectId(subjectId);
    }

    //////////////////////////////////////////////////////
    // CALCULATE ATTENDANCE %
    //////////////////////////////////////////////////////

    public double calculatePercentage(String studentId, String subjectId) {

        long total = attendanceRepository
                .countByStudentStudentIdAndSubjectSubjectId(studentId, subjectId);

        long present = attendanceRepository
                .countByStudentStudentIdAndSubjectSubjectIdAndStatus(
                        studentId, subjectId, "PRESENT");

        if (total == 0) return 0;

        return (present * 100.0) / total;
    }
}