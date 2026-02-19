package com.college.attendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.college.attendance.model.Student;
import com.college.attendance.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // ✅ Save Student
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    // ✅ Get All Students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // ✅ Get Student By ID
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
    }

    // ✅ Update Student
    public Student updateStudent(Long id, Student updatedStudent) {

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));

        existingStudent.setName(updatedStudent.getName());
        existingStudent.setRollNo(updatedStudent.getRollNo());
        existingStudent.setSemester(updatedStudent.getSemester());
        existingStudent.setDepartment(updatedStudent.getDepartment());

        return studentRepository.save(existingStudent);
    }

    // ✅ Delete Student
    public void deleteStudent(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));

        studentRepository.delete(student);
    }
}
