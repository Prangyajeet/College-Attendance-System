package com.college.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.college.attendance.model.ClassSchedule;
import com.college.attendance.service.ClassScheduleService;

@RestController
@RequestMapping("/schedule")
@CrossOrigin(origins = "*")
public class ClassScheduleController {

    @Autowired
    private ClassScheduleService scheduleService;

    // ================================
    // CREATE SCHEDULE
    // ================================
    @PostMapping
    public ClassSchedule createSchedule(
            @RequestBody ClassSchedule schedule,
            @RequestParam String facultyId) {   // ✅ Long → String

        return scheduleService.createSchedule(schedule, facultyId);
    }

    // ================================
    // GET SCHEDULE BY FACULTY
    // ================================
    @GetMapping("/faculty/{facultyId}")
    public List<ClassSchedule> getScheduleByFaculty(@PathVariable String facultyId) {

        System.out.println("Faculty ID received: " + facultyId);
        return scheduleService.getScheduleByFaculty(facultyId);
    }

    // ================================
    // GET ALL
    // ================================
    @GetMapping
    public List<ClassSchedule> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }
}