package com.college.attendance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.college.attendance.dto.DeanReportDTO;
import com.college.attendance.service.DeanService;

@RestController
@RequestMapping("/dean")
@CrossOrigin
public class DeanController {

    @Autowired
    private DeanService deanService;

    // ✅ API: Get full report
    @GetMapping("/report")
    public List<DeanReportDTO> getReport() {
        return deanService.getDeanReport();
    }
}