package com.college.attendance.model;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @Column(name = "student_id")
    private String studentId;   // S0001 from Excel

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    // ✅ FIXED: department_id → dept_id
    @ManyToOne
    @JoinColumn(name = "dept_id", nullable = false)
    private Department department;

    // ✅ FIXED: semester_id → sem_id
    @ManyToOne
    @JoinColumn(name = "sem_id", nullable = false)
    private Semester semester;

    public Student() {}

    public Student(String studentId,
                   String name,
                   String email,
                   String password,
                   Department department,
                   Semester semester) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.department = department;
        this.semester = semester;
    }

    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public Department getDepartment() { return department; }
    public Semester getSemester() { return semester; }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }
}