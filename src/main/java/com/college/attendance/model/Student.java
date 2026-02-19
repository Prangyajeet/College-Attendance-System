package com.college.attendance.model;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "roll_no", unique = true)
    private String rollNo;

    private String semester;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    // Constructors
    public Student() {
    }

    public Student(String name, String rollNo, String semester, Department department) {
        this.name = name;
        this.rollNo = rollNo;
        this.semester = semester;
        this.department = department;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public String getSemester() {
        return semester;
    }

    public Department getDepartment() {
        return department;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
