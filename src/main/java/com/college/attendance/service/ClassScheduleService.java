package com.college.attendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.college.attendance.model.ClassSchedule;
import com.college.attendance.model.User;
import com.college.attendance.repository.ClassScheduleRepository;
import com.college.attendance.repository.UserRepository;

@Service
public class ClassScheduleService {

    @Autowired
    private ClassScheduleRepository classScheduleRepository;

    @Autowired
    private UserRepository userRepository;

    // ====================================================
    // CREATE SCHEDULE
    // ====================================================
    public ClassSchedule createSchedule(ClassSchedule schedule, String facultyId) {

        User faculty = userRepository.findById(facultyId)
                .orElseThrow(() -> new RuntimeException("Faculty not found: " + facultyId));

        schedule.setFaculty(faculty);

        return classScheduleRepository.save(schedule);
    }

    // ====================================================
    // GET SCHEDULE BY FACULTY (Optimized DB Query)
    // ====================================================
    public List<ClassSchedule> getScheduleByFaculty(String facultyId) {

        // Validate faculty exists
        if (!userRepository.existsById(facultyId)) {
            throw new RuntimeException("Faculty not found: " + facultyId);
        }

        return classScheduleRepository.findByFaculty_FacultyId(facultyId);
    }

    // ====================================================
    // GET ALL SCHEDULES
    // ====================================================
    public List<ClassSchedule> getAllSchedules() {
        return classScheduleRepository.findAll();
    }
}