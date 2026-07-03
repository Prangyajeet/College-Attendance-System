package com.college.attendance.model;

import jakarta.persistence.*;

@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @Column(name = "subject_id")
    private String subjectId;   // SUB001 from Excel

    @Column(nullable = false)
    private String subjectName;

    // ✅ FIXED: department_id → dept_id
    @ManyToOne
    @JoinColumn(name = "dept_id", nullable = false)
    private Department department;

    // ✅ FIXED: semester_id → sem_id
    @ManyToOne
    @JoinColumn(name = "sem_id", nullable = false)
    private Semester semester;

    public Subject() {}

    public Subject(String subjectId,
                   String subjectName,
                   Department department,
                   Semester semester) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.department = department;
        this.semester = semester;
    }

    public String getSubjectId() { return subjectId; }
    public String getSubjectName() { return subjectName; }
    public Department getDepartment() { return department; }
    public Semester getSemester() { return semester; }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }
}