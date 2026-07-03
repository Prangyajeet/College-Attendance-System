package com.college.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.college.attendance.model.Subject;
import com.college.attendance.service.SubjectService;

@RestController
@RequestMapping("/subjects")
@CrossOrigin(origins = "*")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/faculty/{facultyId}")
    public List<Subject> getSubjectsByFaculty(
            @PathVariable String facultyId) {

        return subjectService.getSubjectsByFaculty(facultyId);
    }
}