package com.college.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.college.attendance.model.Subject;
import com.college.attendance.service.SubjectService;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    // Add Subject
    @PostMapping
    public Subject addSubject(@RequestBody Subject subject) {
        return subjectService.saveSubject(subject);
    }

    // Get All Subjects
    @GetMapping
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }
}
