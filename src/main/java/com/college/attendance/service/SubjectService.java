package com.college.attendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import com.college.attendance.model.Subject;
import com.college.attendance.repository.FacultySubjectMapRepository;

@Service
public class SubjectService {

    @Autowired
    private FacultySubjectMapRepository mapRepository;

    public List<Subject> getSubjectsByFaculty(String facultyId) {

        return mapRepository.findByFaculty_FacultyId(facultyId)
                .stream()
                .map(map -> map.getSubject())
                .collect(Collectors.toList());
    }
}