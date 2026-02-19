package com.college.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.college.attendance.model.Attendance;
import com.college.attendance.service.AttendanceService;
import com.college.attendance.dto.AttendanceResponseDTO;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping
    public AttendanceResponseDTO markAttendance(
            @RequestBody Attendance attendance) {

        Attendance saved =
                attendanceService.markAttendance(attendance);

        return new AttendanceResponseDTO(
                saved.getId(),
                saved.getDate(),
                saved.getStatus(),
                saved.getStudent().getId(),
                saved.getStudent().getName(),
                saved.getSubject().getId(),
                saved.getSubject().getSubjectName()
        );
    }

    @GetMapping
    public List<AttendanceResponseDTO> getAllAttendance() {

        return attendanceService.getAllAttendance()
                .stream()
                .map(a -> new AttendanceResponseDTO(
                        a.getId(),
                        a.getDate(),
                        a.getStatus(),
                        a.getStudent().getId(),
                        a.getStudent().getName(),
                        a.getSubject().getId(),
                        a.getSubject().getSubjectName()
                ))
                .toList();
    }

    @GetMapping("/percentage")
    public double getPercentage(
            @RequestParam Long studentId,
            @RequestParam Long subjectId) {

        return attendanceService.calculatePercentage(studentId, subjectId);
    }
}
