package com.college.attendance.model;

import jakarta.persistence.*;

@Entity
@Table(name = "semesters",
       uniqueConstraints = @UniqueConstraint(columnNames = "semester_number"))
public class Semester {

    @Id
    @Column(name = "sem_id")
    private String semId;   // S1, S2, S3 from Excel

    @Column(name = "semester_number", nullable = false, unique = true)
    private Integer semesterNumber;

    public Semester() {}

    public Semester(String semId, Integer semesterNumber) {
        this.semId = semId;
        this.semesterNumber = semesterNumber;
    }

    public String getSemId() { return semId; }

    public Integer getSemesterNumber() { return semesterNumber; }

    public void setSemId(String semId) {
        this.semId = semId;
    }

    public void setSemesterNumber(Integer semesterNumber) {
        this.semesterNumber = semesterNumber;
    }
}