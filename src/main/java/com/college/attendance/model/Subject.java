package com.college.attendance.model;

import jakarta.persistence.*;

@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subjectName;

    private String semester;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private User faculty;

    // Constructors
    public Subject() {
    }

    public Subject(String subjectName, String semester, Department department, User faculty) {
        this.subjectName = subjectName;
        this.semester = semester;
        this.department = department;
        this.faculty = faculty;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getSemester() {
        return semester;
    }

    public Department getDepartment() {
        return department;
    }

    public User getFaculty() {
        return faculty;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setFaculty(User faculty) {
        this.faculty = faculty;
    }
}
