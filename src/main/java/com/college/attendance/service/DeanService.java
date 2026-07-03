package com.college.attendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.attendance.dto.DeanReportDTO;
import com.college.attendance.repository.AttendanceRepository;

@Service
public class DeanService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    // ✅ Get full dean report
    public List<DeanReportDTO> getDeanReport() {
        return attendanceRepository.getDeanReport();
    }
}