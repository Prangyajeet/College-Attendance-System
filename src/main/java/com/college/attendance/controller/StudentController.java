package com.college.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.college.attendance.model.Student;
import com.college.attendance.service.StudentService;

@RestController
@RequestMapping("/students")
@CrossOrigin("*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    //////////////////////////////////////////////////
    // GET students by SUBJECT
    //////////////////////////////////////////////////
    @GetMapping("/subject/{subjectId}")
    public List<Student> getStudentsBySubject(@PathVariable String subjectId) {
        return studentService.getStudentsBySubject(subjectId);
    }

    //////////////////////////////////////////////////
    // GET students by SEMESTER
    //////////////////////////////////////////////////
    @GetMapping("/semester/{semId}")
    public List<Student> getStudentsBySemester(@PathVariable String semId) {
        return studentService.getStudentsBySemester(semId);
    }

    //////////////////////////////////////////////////
    // 🔥 STUDENT LOGIN (UPDATED + DEBUG + SAFE)
    //////////////////////////////////////////////////
    @PostMapping("/login")
    public Student login(@RequestBody Student student) {

        if(student == null){
            System.out.println("❌ REQUEST BODY NULL");
            return null;
        }

        if(student.getEmail() == null || student.getPassword() == null){
            System.out.println("❌ EMAIL OR PASSWORD NULL");
            return null;
        }

        return studentService.login(student.getEmail(), student.getPassword());
    }
}