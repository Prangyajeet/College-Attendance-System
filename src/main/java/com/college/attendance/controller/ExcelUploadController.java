package com.college.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.college.attendance.service.ExcelUploadService;

@RestController
@RequestMapping("/api/excel")
@CrossOrigin(origins = "*")
public class ExcelUploadController {

    @Autowired
    private ExcelUploadService excelUploadService;

    // 1️⃣ Departments
    @PostMapping("/departments")
    public String uploadDepartments(@RequestParam("file") MultipartFile file) throws Exception {
        excelUploadService.importDepartments(file);
        return "Departments uploaded successfully";
    }

    // 2️⃣ Semesters
    @PostMapping("/semesters")
    public String uploadSemesters(@RequestParam("file") MultipartFile file) throws Exception {
        excelUploadService.importSemesters(file);
        return "Semesters uploaded successfully";
    }

    // 3️⃣ Faculty
    @PostMapping("/faculty")
    public String uploadFaculty(@RequestParam("file") MultipartFile file) throws Exception {
        excelUploadService.importFaculty(file);
        return "Faculty uploaded successfully";
    }

    // 4️⃣ Subjects
    @PostMapping("/subjects")
    public String uploadSubjects(@RequestParam("file") MultipartFile file) throws Exception {
        excelUploadService.importSubjects(file);
        return "Subjects uploaded successfully";
    }

    // 5️⃣ Students
    @PostMapping("/students")
    public String uploadStudents(@RequestParam("file") MultipartFile file) throws Exception {
        excelUploadService.importStudents(file);
        return "Students uploaded successfully";
    }

    // 6️⃣ Faculty Subject Map
    @PostMapping("/faculty_subject_map")
    public String uploadFacultySubjectMap(@RequestParam("file") MultipartFile file) throws Exception {
        excelUploadService.importFacultySubjectMap(file);
        return "Faculty Subject Map uploaded successfully";
    }

    // 7️⃣ Class Schedule ✅ NEW
    @PostMapping("/class_schedule")
    public String uploadClassSchedule(@RequestParam("file") MultipartFile file) throws Exception {
        excelUploadService.importClassSchedule(file);
        return "Class Schedule uploaded successfully";
    }
}