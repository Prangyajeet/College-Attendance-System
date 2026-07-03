package com.college.attendance.service;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.college.attendance.model.*;
import com.college.attendance.repository.*;

@Service
@Transactional
public class ExcelUploadService {

    @Autowired private DepartmentRepository departmentRepository;
    @Autowired private SemesterRepository semesterRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private SubjectRepository subjectRepository;
    @Autowired private StudentRepository studentRepository;
    @Autowired private FacultySubjectMapRepository facultySubjectMapRepository;
    @Autowired private ClassScheduleRepository classScheduleRepository;

    // ====================================================
    // 1️⃣ IMPORT DEPARTMENTS
    // ====================================================
    public void importDepartments(MultipartFile file) throws Exception {

        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            Row row = sheet.getRow(i);
            if (row == null) continue;

            String deptId = row.getCell(0).getStringCellValue().trim();
            String deptName = row.getCell(1).getStringCellValue().trim();

            if (!departmentRepository.existsById(deptId)) {
                Department department = new Department();
                department.setDeptId(deptId);
                department.setDepartmentName(deptName);
                departmentRepository.save(department);
            }
        }

        workbook.close();
    }

    // ====================================================
    // 2️⃣ IMPORT SEMESTERS
    // ====================================================
    public void importSemesters(MultipartFile file) throws Exception {

        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            Row row = sheet.getRow(i);
            if (row == null) continue;

            String semId = row.getCell(0).getStringCellValue().trim();
            int semNumber = (int) row.getCell(1).getNumericCellValue();

            if (!semesterRepository.existsById(semId)) {
                Semester semester = new Semester();
                semester.setSemId(semId);
                semester.setSemesterNumber(semNumber);
                semesterRepository.save(semester);
            }
        }

        workbook.close();
    }

    // ====================================================
    // 3️⃣ IMPORT FACULTY
    // ====================================================
    public void importFaculty(MultipartFile file) throws Exception {

        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            Row row = sheet.getRow(i);
            if (row == null) continue;

            String facultyId = row.getCell(0).getStringCellValue().trim();
            String name = row.getCell(1).getStringCellValue().trim();
            String email = row.getCell(2).getStringCellValue().trim();
            String password = row.getCell(3).getStringCellValue().trim();
            String deptId = row.getCell(4).getStringCellValue().trim();

            Department department = departmentRepository.findById(deptId)
                    .orElseThrow(() -> new RuntimeException("Department not found: " + deptId));

            if (!userRepository.existsById(facultyId)) {
                User faculty = new User();
                faculty.setFacultyId(facultyId);
                faculty.setName(name);
                faculty.setEmail(email);
                faculty.setPassword(password);
                faculty.setRole("FACULTY");
                faculty.setDepartment(department);
                userRepository.save(faculty);
            }
        }

        workbook.close();
    }

    // ====================================================
    // 4️⃣ IMPORT SUBJECTS
    // ====================================================
    public void importSubjects(MultipartFile file) throws Exception {

        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            Row row = sheet.getRow(i);
            if (row == null) continue;

            String subjectId = row.getCell(0).getStringCellValue().trim();
            String subjectName = row.getCell(1).getStringCellValue().trim();
            String deptId = row.getCell(2).getStringCellValue().trim();
            String semId = row.getCell(3).getStringCellValue().trim();

            Department department = departmentRepository.findById(deptId)
                    .orElseThrow(() -> new RuntimeException("Department not found"));

            Semester semester = semesterRepository.findById(semId)
                    .orElseThrow(() -> new RuntimeException("Semester not found"));

            if (!subjectRepository.existsById(subjectId)) {
                Subject subject = new Subject();
                subject.setSubjectId(subjectId);
                subject.setSubjectName(subjectName);
                subject.setDepartment(department);
                subject.setSemester(semester);
                subjectRepository.save(subject);
            }
        }

        workbook.close();
    }

    // ====================================================
    // 5️⃣ IMPORT STUDENTS
    // ====================================================
    public void importStudents(MultipartFile file) throws Exception {

        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            Row row = sheet.getRow(i);
            if (row == null) continue;

            String studentId = row.getCell(0).getStringCellValue().trim();
            String name = row.getCell(1).getStringCellValue().trim();
            String email = row.getCell(2).getStringCellValue().trim();
            String password = row.getCell(3).getStringCellValue().trim();
            String deptId = row.getCell(4).getStringCellValue().trim();
            String semId = row.getCell(5).getStringCellValue().trim();

            Department department = departmentRepository.findById(deptId)
                    .orElseThrow(() -> new RuntimeException("Department not found"));

            Semester semester = semesterRepository.findById(semId)
                    .orElseThrow(() -> new RuntimeException("Semester not found"));

            if (!studentRepository.existsById(studentId)) {
                Student student = new Student();
                student.setStudentId(studentId);
                student.setName(name);
                student.setEmail(email);
                student.setPassword(password);
                student.setDepartment(department);
                student.setSemester(semester);
                studentRepository.save(student);
            }
        }

        workbook.close();
    }

    // ====================================================
    // 6️⃣ IMPORT FACULTY SUBJECT MAP
    // ====================================================
    public void importFacultySubjectMap(MultipartFile file) throws Exception {

        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            Row row = sheet.getRow(i);
            if (row == null) continue;

            String mapId = row.getCell(0).getStringCellValue().trim();
            String facultyId = row.getCell(1).getStringCellValue().trim();
            String subjectId = row.getCell(2).getStringCellValue().trim();
            String deptId = row.getCell(3).getStringCellValue().trim();
            String semId = row.getCell(4).getStringCellValue().trim();

            User faculty = userRepository.findById(facultyId)
                    .orElseThrow(() -> new RuntimeException("Faculty not found"));

            Subject subject = subjectRepository.findById(subjectId)
                    .orElseThrow(() -> new RuntimeException("Subject not found"));

            Department department = departmentRepository.findById(deptId)
                    .orElseThrow(() -> new RuntimeException("Department not found"));

            Semester semester = semesterRepository.findById(semId)
                    .orElseThrow(() -> new RuntimeException("Semester not found"));

            if (!facultySubjectMapRepository.existsById(mapId)) {
                FacultySubjectMap map = new FacultySubjectMap();
                map.setMapId(mapId);
                map.setFaculty(faculty);
                map.setSubject(subject);
                map.setDepartment(department);
                map.setSemester(semester);
                facultySubjectMapRepository.save(map);
            }
        }

        workbook.close();
    }

    // ====================================================
    // 7️⃣ IMPORT CLASS SCHEDULE
    // ====================================================
    public void importClassSchedule(MultipartFile file) throws Exception {

        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            Row row = sheet.getRow(i);
            if (row == null) continue;

            String facultyId = row.getCell(0).getStringCellValue().trim();
            String subjectId = row.getCell(1).getStringCellValue().trim();

            LocalDate classDate = LocalDate.parse(row.getCell(2).getStringCellValue().trim());
            LocalTime startTime = LocalTime.parse(row.getCell(3).getStringCellValue().trim());
            LocalTime endTime = LocalTime.parse(row.getCell(4).getStringCellValue().trim());
            double totalHours = row.getCell(5).getNumericCellValue();

            User faculty = userRepository.findById(facultyId)
                    .orElseThrow(() -> new RuntimeException("Faculty not found: " + facultyId));

            Subject subject = subjectRepository.findById(subjectId)
                    .orElseThrow(() -> new RuntimeException("Subject not found: " + subjectId));

            ClassSchedule schedule = new ClassSchedule();
            schedule.setFaculty(faculty);
            schedule.setSubject(subject);
            schedule.setClassDate(classDate);
            schedule.setStartTime(startTime);
            schedule.setEndTime(endTime);
            schedule.setTotalHours(totalHours);

            classScheduleRepository.save(schedule);
        }

        workbook.close();
    }
}