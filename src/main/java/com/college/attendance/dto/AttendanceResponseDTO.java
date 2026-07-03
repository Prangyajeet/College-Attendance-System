package com.college.attendance.dto;

import java.time.LocalDate;

public class AttendanceResponseDTO {

    private Long attendanceId;
    private LocalDate date;
    private String status;

    private String studentId;
    private String studentName;

    private String subjectId;
    private String subjectName;

    public AttendanceResponseDTO(
            Long attendanceId,
            LocalDate date,
            String status,
            String studentId,
            String studentName,
            String subjectId,
            String subjectName) {

        this.attendanceId = attendanceId;
        this.date = date;
        this.status = status;
        this.studentId = studentId;
        this.studentName = studentName;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

    public Long getAttendanceId() { return attendanceId; }
    public LocalDate getDate() { return date; }
    public String getStatus() { return status; }
    public String getStudentId() { return studentId; }
    public String getStudentName() { return studentName; }
    public String getSubjectId() { return subjectId; }
    public String getSubjectName() { return subjectName; }
}