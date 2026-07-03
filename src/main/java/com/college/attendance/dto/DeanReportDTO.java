package com.college.attendance.dto;

public class DeanReportDTO {

    private String studentName;
    private String department;
    private int semester;
    private String subjectName;
    private double attendancePercentage;

    // Constructor
    public DeanReportDTO(String studentName, String department,
                         int semester, String subjectName,
                         double attendancePercentage) {
        this.studentName = studentName;
        this.department = department;
        this.semester = semester;
        this.subjectName = subjectName;
        this.attendancePercentage = attendancePercentage;
    }

    // Getters
    public String getStudentName() {
        return studentName;
    }

    public String getDepartment() {
        return department;
    }

    public int getSemester() {
        return semester;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public double getAttendancePercentage() {
        return attendancePercentage;
    }
}