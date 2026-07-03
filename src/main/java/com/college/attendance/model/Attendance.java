package com.college.attendance.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private String status;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "faculty_id", nullable = false)
    private User faculty;

    public Attendance() {}

    public Attendance(LocalDate date,
                      String status,
                      Student student,
                      Subject subject,
                      User faculty) {
        this.date = date;
        this.status = status;
        this.student = student;
        this.subject = subject;
        this.faculty = faculty;
    }

    public Long getId() { return id; }

    public LocalDate getDate() { return date; }

    public String getStatus() { return status; }

    public Student getStudent() { return student; }

    public Subject getSubject() { return subject; }

    public User getFaculty() { return faculty; }

    public void setId(Long id) { this.id = id; }

    public void setDate(LocalDate date) { this.date = date; }

    public void setStatus(String status) { this.status = status; }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setFaculty(User faculty) {
        this.faculty = faculty;
    }
}