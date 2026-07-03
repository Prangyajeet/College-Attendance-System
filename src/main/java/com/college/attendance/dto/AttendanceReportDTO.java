package com.college.attendance.dto;

public class AttendanceReportDTO {

    private String studentId;
    private String studentName;
    private String email;
    private double percentage;

    public AttendanceReportDTO() {}

    public AttendanceReportDTO(
            String studentId,
            String studentName,
            String email,
            double percentage) {

        this.studentId = studentId;
        this.studentName = studentName;
        this.email = email;
        this.percentage = percentage;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getEmail() {
        return email;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}