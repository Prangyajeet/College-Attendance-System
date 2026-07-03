package com.college.attendance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.college.attendance.model.FacultySubjectMap;
import com.college.attendance.repository.FacultySubjectMapRepository;

@RestController
@RequestMapping("/faculty")
@CrossOrigin
public class FacultyController {

    @Autowired
    private FacultySubjectMapRepository facultySubjectMapRepository;

    // ✅ GET SUBJECTS FOR LOGGED FACULTY
    @GetMapping("/{facultyId}/subjects")
    public List<FacultySubjectMap> getSubjects(@PathVariable String facultyId){

        System.out.println("Faculty ID received: " + facultyId);

        return facultySubjectMapRepository.findByFaculty_FacultyId(facultyId);
    }

}