package com.college.attendance.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.college.attendance.model.User;
import com.college.attendance.model.Department;
import com.college.attendance.model.Semester;
import com.college.attendance.model.Student;
import com.college.attendance.model.Subject;
import com.college.attendance.model.FacultySubject;

import com.college.attendance.repository.UserRepository;
import com.college.attendance.repository.StudentRepository;
import com.college.attendance.repository.SubjectRepository;
import com.college.attendance.repository.DepartmentRepository;
import com.college.attendance.repository.SemesterRepository;
import com.college.attendance.repository.FacultySubjectRepository;
import com.college.attendance.repository.AttendanceRepository;
import com.college.attendance.repository.ClassScheduleRepository;
import com.college.attendance.repository.FacultySubjectMapRepository;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private SemesterRepository semesterRepository;

    @Autowired
    private FacultySubjectRepository facultySubjectRepository;

    // ⭐ NEW REPOSITORIES
    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private ClassScheduleRepository classScheduleRepository;

    @Autowired
    private FacultySubjectMapRepository facultySubjectMapRepository;


    // 1️⃣ Dashboard Statistics
    @GetMapping("/dashboard")
    public Map<String, Long> dashboardStats(){

        Map<String, Long> stats = new HashMap<>();

        stats.put("faculty", userRepository.countByRole("FACULTY"));
        stats.put("students", studentRepository.count());
        stats.put("subjects", subjectRepository.count());
        stats.put("departments", departmentRepository.count());

        return stats;
    }


    // 2️⃣ Add Faculty
    @PostMapping("/faculty")
    public User addFaculty(@RequestBody User user){

        user.setRole("FACULTY");

        return userRepository.save(user);
    }


    // 3️⃣ Get All Faculty
    @GetMapping("/faculty")
    public List<User> getAllFaculty(){

        return userRepository.findByRoleOrderByFacultyIdAsc("FACULTY");
    }


    // 4️⃣ Update Faculty
    @PutMapping("/faculty/{facultyId}")
    public User updateFaculty(@PathVariable String facultyId,
                              @RequestBody User updatedUser){

        User user = userRepository.findById(facultyId)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setDepartment(updatedUser.getDepartment());

        return userRepository.save(user);
    }


    @DeleteMapping("/faculty/{facultyId}")
    public String deleteFaculty(@PathVariable String facultyId){

        // 1️⃣ Delete from faculty_subjects (IMPORTANT)
        facultySubjectRepository.deleteByFaculty_FacultyId(facultyId);

        // 2️⃣ Delete from faculty_subject_map
        facultySubjectMapRepository.deleteByFaculty_FacultyId(facultyId);

        // 3️⃣ Delete attendance
        attendanceRepository.deleteByFacultyFacultyId(facultyId);

        // 4️⃣ Delete class schedule
        classScheduleRepository.deleteByFaculty_FacultyId(facultyId);

        // 5️⃣ Finally delete faculty
        userRepository.deleteById(facultyId);

        return "Faculty deleted successfully";
    }


    // 6️⃣ Get All Students
    @GetMapping("/students")
    public List<Student> getAllStudents(){

        return studentRepository.findAll();
    }


    // 7️⃣ Add Student
    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student){

        return studentRepository.save(student);
    }


    // 8️⃣ Delete Student
    @DeleteMapping("/students/{studentId}")
    public String deleteStudent(@PathVariable String studentId){

        // delete attendance first
        attendanceRepository.deleteByStudentStudentId(studentId);

        // then delete student
        studentRepository.deleteById(studentId);

        return "Student deleted successfully";
    }


    // 9️⃣ Add Subject
    @PostMapping("/subjects")
    public Subject addSubject(@RequestBody Subject subject){

        return subjectRepository.save(subject);
    }


    // 🔟 Get All Subjects
    @GetMapping("/subjects")
    public List<Subject> getAllSubjects(){

        return subjectRepository.findAll();
    }


    // 11️⃣ Delete Subject
    @DeleteMapping("/subjects/{subjectId}")
    public String deleteSubject(@PathVariable String subjectId){

        subjectRepository.deleteById(subjectId);

        return "Subject deleted successfully";
    }


    // 12️⃣ Get All Departments
    @GetMapping("/departments")
    public List<Department> getAllDepartments(){

        return departmentRepository.findAll();
    }


    // 13️⃣ Add Department
    @PostMapping("/departments")
    public Department addDepartment(@RequestBody Department department){

        return departmentRepository.save(department);
    }


    // 14️⃣ Delete Department
    @DeleteMapping("/departments/{deptId}")
    public String deleteDepartment(@PathVariable String deptId){

        departmentRepository.deleteById(deptId);

        return "Department deleted successfully";
    }


    // 15️⃣ Get All Semesters
    @GetMapping("/semesters")
    public List<Semester> getAllSemesters(){

        return semesterRepository.findAll();
    }


    // 16️⃣ Add Semester
    @PostMapping("/semesters")
    public Semester addSemester(@RequestBody Semester semester){

        return semesterRepository.save(semester);
    }


    // 17️⃣ Delete Semester
    @DeleteMapping("/semesters/{semId}")
    public String deleteSemester(@PathVariable String semId){

        semesterRepository.deleteById(semId);

        return "Semester deleted successfully";
    }


    // 18️⃣ Assign Subject to Faculty
    @PostMapping("/assign")
    public FacultySubject assignSubject(@RequestBody FacultySubject fs){

        return facultySubjectRepository.save(fs);
    }


    // 19️⃣ Get All Assignments
    @GetMapping("/assignments")
    public List<FacultySubject> getAssignments(){

        return facultySubjectRepository.findAll();
    }
    
 // ⭐ NEW - Filter Students by Department
    @GetMapping("/students/department/{deptId}")
    public List<Student> getStudentsByDepartment(@PathVariable String deptId){

        return studentRepository.findByDepartment_DeptId(deptId);

    }

}