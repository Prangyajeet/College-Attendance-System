package com.college.attendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.college.attendance.model.Student;
import com.college.attendance.model.Subject;
import com.college.attendance.repository.StudentRepository;
import com.college.attendance.repository.SubjectRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    // Load students by subject
    public List<Student> getStudentsBySubject(String subjectId) {

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        String semId = subject.getSemester().getSemId();
        String deptId = subject.getDepartment().getDeptId();

        return studentRepository.findBySemester_SemIdAndDepartment_DeptId(
                semId,
                deptId
        );
    }

    // Optional: students by semester (for testing)
    public List<Student> getStudentsBySemester(String semId) {
        throw new RuntimeException("Use subject-based filtering instead");
    }

//////////////////////////////////////////////////
//🔥 LOGIN METHOD (UPDATED)
//////////////////////////////////////////////////

    public Student login(String email, String password) {

        // remove spaces (IMPORTANT FIX)
        email = email.trim();
        password = password.trim();

        Student student = studentRepository.findByEmail(email);

        if(student == null){
            System.out.println("❌ EMAIL NOT FOUND: " + email);
            return null;
        }

        System.out.println("DB PASSWORD: " + student.getPassword());
        System.out.println("INPUT PASSWORD: " + password);

        if(!student.getPassword().trim().equals(password)){
            System.out.println("❌ PASSWORD MISMATCH");
            return null;
        }

        System.out.println("✅ LOGIN SUCCESS");

        return student;
    }
}