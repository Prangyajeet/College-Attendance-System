package com.college.attendance.model;

import jakarta.persistence.*;

@Entity
@Table(name = "faculty_subject_map")
public class FacultySubjectMap {

    @Id
    @Column(name = "map_id")
    private String mapId;   // M001 from Excel

    @ManyToOne
    @JoinColumn(name = "faculty_id", nullable = false)
    private User faculty;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "dept_id", nullable = false)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "sem_id", nullable = false)
    private Semester semester;

    public FacultySubjectMap() {}

    public FacultySubjectMap(String mapId,
                             User faculty,
                             Subject subject,
                             Department department,
                             Semester semester) {
        this.mapId = mapId;
        this.faculty = faculty;
        this.subject = subject;
        this.department = department;
        this.semester = semester;
    }

    public String getMapId() { return mapId; }
    public User getFaculty() { return faculty; }
    public Subject getSubject() { return subject; }
    public Department getDepartment() { return department; }
    public Semester getSemester() { return semester; }

    public void setMapId(String mapId) { this.mapId = mapId; }
    public void setFaculty(User faculty) { this.faculty = faculty; }
    public void setSubject(Subject subject) { this.subject = subject; }
    public void setDepartment(Department department) { this.department = department; }
    public void setSemester(Semester semester) { this.semester = semester; }
}