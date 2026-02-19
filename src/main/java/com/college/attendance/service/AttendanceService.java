package com.college.attendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.college.attendance.model.Attendance;
import com.college.attendance.model.Subject;
import com.college.attendance.model.Student;
import com.college.attendance.repository.AttendanceRepository;
import com.college.attendance.repository.SubjectRepository;
import com.college.attendance.repository.StudentRepository;
import com.college.attendance.security.UserSession;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private StudentRepository studentRepository;

    public Attendance markAttendance(Attendance attendance) {

        // 🔥 Get logged-in faculty from session
        Long loggedInFacultyId = UserSession.getLoggedInUserId();

        if (loggedInFacultyId == null) {
            throw new RuntimeException("User not logged in");
        }

        Long subjectId = attendance.getSubject().getId();
        Long studentId = attendance.getStudent().getId();

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // 🔐 Faculty validation
        if (!subject.getFaculty().getId().equals(loggedInFacultyId)) {
            throw new RuntimeException("Not authorized for this subject");
        }

        attendance.setSubject(subject);
        attendance.setStudent(student);

        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    public double calculatePercentage(Long studentId, Long subjectId) {

        long total = attendanceRepository
                .countByStudentIdAndSubjectId(studentId, subjectId);

        long present = attendanceRepository
                .countByStudentIdAndSubjectIdAndStatus(
                        studentId,
                        subjectId,
                        "PRESENT"
                );

        if (total == 0) {
            return 0;
        }

        return (present * 100.0) / total;
    }
}
