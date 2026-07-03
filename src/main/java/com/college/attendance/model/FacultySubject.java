package com.college.attendance.model;

import jakarta.persistence.*;

@Entity
@Table(name = "faculty_subjects")
public class FacultySubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private User faculty;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    public FacultySubject() {}

    public Long getId() {
        return id;
    }

    public User getFaculty() {
        return faculty;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setFaculty(User faculty) {
        this.faculty = faculty;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}