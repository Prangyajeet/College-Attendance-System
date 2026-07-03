package com.college.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.college.attendance.model.Attendance;
import com.college.attendance.repository.AttendanceRepository;   // ✅ IMPORT
import com.college.attendance.service.AttendanceService;

@RestController
@RequestMapping("/attendance")
@CrossOrigin("*")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    // ✅ ADD THIS (FIX)
    @Autowired
    private AttendanceRepository attendanceRepository;

    // Save attendance
    @PostMapping
    public Attendance markAttendance(
            @RequestBody Attendance attendance,
            @RequestParam String facultyId) {

        return attendanceService.markAttendance(attendance, facultyId);
    }

    // Attendance report by subject
    @GetMapping("/report/{subjectId}")
    public List<Attendance> getAttendanceReport(@PathVariable String subjectId) {

        return attendanceService.getAttendanceReport(subjectId);
    }

    // Attendance by date (faculty specific)
    @GetMapping("/date")
    public List<Attendance> getAttendanceByDate(
            @RequestParam String date,
            @RequestParam String facultyId) {

        return attendanceService.getAttendanceByDate(date, facultyId);
    }

    // Get all attendance
    @GetMapping
    public List<Attendance> getAllAttendance(){
        return attendanceService.getAllAttendance();
    }

    // ✅ FIXED STUDENT API
    @GetMapping("/student/{studentId}")
    public List<Attendance> getAttendanceByStudent(@PathVariable String studentId){
        return attendanceRepository.findByStudent_StudentId(studentId);
    }
}