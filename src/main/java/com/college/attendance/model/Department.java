package com.college.attendance.model;

import jakarta.persistence.*;

@Entity
@Table(name = "departments",
       uniqueConstraints = @UniqueConstraint(columnNames = "department_name"))
public class Department {

    @Id
    @Column(name = "dept_id")
    private String deptId;   // D01, D02 from Excel

    @Column(name = "department_name", nullable = false, unique = true)
    private String departmentName;

    public Department() {}

    public Department(String deptId, String departmentName) {
        this.deptId = deptId;
        this.departmentName = departmentName;
    }

    public String getDeptId() { return deptId; }

    public String getDepartmentName() { return departmentName; }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}