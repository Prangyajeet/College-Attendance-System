package com.college.attendance.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "class_schedule")
public class ClassSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate classDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private double totalHours;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private User faculty;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    public ClassSchedule() {}

    public Long getId() { return id; }

    public LocalDate getClassDate() { return classDate; }

    public LocalTime getStartTime() { return startTime; }

    public LocalTime getEndTime() { return endTime; }

    public double getTotalHours() { return totalHours; }

    public User getFaculty() { return faculty; }

    public Subject getSubject() { return subject; }

    public void setId(Long id) { this.id = id; }

    public void setClassDate(LocalDate classDate) { this.classDate = classDate; }

    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }

    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }

    public void setTotalHours(double totalHours) { this.totalHours = totalHours; }

    public void setFaculty(User faculty) { this.faculty = faculty; }

    public void setSubject(Subject subject) { this.subject = subject; }
}