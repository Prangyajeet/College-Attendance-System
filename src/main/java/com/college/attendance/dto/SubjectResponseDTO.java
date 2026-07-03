package com.college.attendance.dto;

public class SubjectResponseDTO {

    private String subjectId;
    private String subjectName;
    private Integer semesterNumber;

    public SubjectResponseDTO() {}

    public SubjectResponseDTO(
            String subjectId,
            String subjectName,
            Integer semesterNumber) {

        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.semesterNumber = semesterNumber;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public Integer getSemesterNumber() {
        return semesterNumber;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setSemesterNumber(Integer semesterNumber) {
        this.semesterNumber = semesterNumber;
    }
}