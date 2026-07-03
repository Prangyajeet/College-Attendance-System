package com.college.attendance.dto;

public class LoginResponseDTO {

    private String facultyId;
    private String name;
    private String role;
    private String department;
    private String message;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(String facultyId,
                            String name,
                            String role,
                            String department,
                            String message) {

        this.facultyId = facultyId;
        this.name = name;
        this.role = role;
        this.department = department;
        this.message = message;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}