package com.college.attendance.dto;

import java.time.LocalDate;

public class AttendanceResponseDTO {

    private Long id;
    private LocalDate date;
    private String status;

    private Long studentId;
    private String studentName;

    private Long subjectId;
    private String subjectName;

    public AttendanceResponseDTO() {
    }

    public AttendanceResponseDTO(
            Long id,
            LocalDate date,
            String status,
            Long studentId,
            String studentName,
            Long subjectId,
            String subjectName) {

        this.id = id;
        this.date = date;
        this.status = status;
        this.studentId = studentId;
        this.studentName = studentName;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

    // Getters and Setters

    public Long getId() { return id; }
    public LocalDate getDate() { return date; }
    public String getStatus() { return status; }
    public Long getStudentId() { return studentId; }
    public String getStudentName() { return studentName; }
    public Long getSubjectId() { return subjectId; }
    public String getSubjectName() { return subjectName; }

    public void setId(Long id) { this.id = id; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setStatus(String status) { this.status = status; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public void setSubjectId(Long subjectId) { this.subjectId = subjectId; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }
}
