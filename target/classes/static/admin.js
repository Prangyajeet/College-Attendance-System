window.onload = function(){
    loadDashboard();
}

function loadDashboard(){
    fetch("http://localhost:8080/admin/dashboard")
    .then(res => res.json())
    .then(data => {
        document.getElementById("facultyCount").innerText = data.faculty;
        document.getElementById("studentCount").innerText = data.students;
        document.getElementById("subjectCount").innerText = data.subjects;
        document.getElementById("departmentCount").innerText = data.departments;
    })
}

//////////////////////////////////////////////////
// PAGE LOADER
//////////////////////////////////////////////////

function loadPage(page){

    fetch(page)
    .then(res => res.text())
    .then(data => {

        document.getElementById("contentArea").innerHTML = data;

        if(page.includes("subjects.html")) loadSubjects();
        if(page.includes("departments.html")) loadDepartments();
        if(page.includes("students.html")) loadStudents();
        if(page.includes("faculty.html")) loadFaculty();

        if(page.includes("assign.html")){
            setTimeout(() => {
                loadFacultyDropdown();
                loadSubjectDropdown();
                loadAssignments();
            }, 300);
        }

        if(page.includes("attendance.html")){
            setTimeout(() => {
                loadAttendance();
                loadAttendanceFacultyDropdown();
            }, 300);
        }

    })
}

function logout(){
    localStorage.clear();
    window.location.href="login.html";
}

//////////////////////////////////////////////////
// FACULTY
//////////////////////////////////////////////////

function loadFaculty(){
    fetch("http://localhost:8080/admin/faculty")
    .then(res => res.json())
    .then(data => {

        let rows = data.map(f => `
        <tr>
            <td>${f.facultyId}</td>
            <td>${f.name}</td>
            <td>${f.email}</td>
            <td>${f.department?.deptId || ''}</td>
            <td>
                <button onclick="deleteFaculty('${f.facultyId}')">Delete</button>
            </td>
        </tr>
        `).join("");

        document.getElementById("facultyBody").innerHTML = rows;
    })
}

function deleteFaculty(id){
    if(!confirm("Are you sure you want to delete this faculty?")) return;

    fetch("http://localhost:8080/admin/faculty/" + id,{ method:"DELETE"})
    .then(res => res.text())
    .then(msg =>{
        alert(msg);
        loadFaculty();
    })
}

//////////////////////////////////////////////////
// STUDENTS
//////////////////////////////////////////////////

function loadStudents(){
    fetch("http://localhost:8080/admin/students")
    .then(res => res.json())
    .then(data => renderStudents(data))
}

function renderStudents(data){
    let rows = data.map(s => `
    <tr>
        <td>${s.studentId}</td>
        <td>${s.name}</td>
        <td>${s.email}</td>
        <td>${s.department?.deptId || ''}</td>
        <td>Sem ${s.semester?.semesterNumber || ''}</td>
        <td>
            <button onclick="deleteStudent('${s.studentId}')">Delete</button>
        </td>
    </tr>
    `).join("");

    document.getElementById("studentBody").innerHTML = rows;
}

function deleteStudent(id){
    if(!confirm("Are you sure you want to delete this student?")) return;

    fetch("http://localhost:8080/admin/students/" + id,{ method:"DELETE"})
    .then(res => res.text())
    .then(msg =>{
        alert(msg);
        loadStudents();
    })
}

//////////////////////////////////////////////////
// SUBJECTS
//////////////////////////////////////////////////

function loadSubjects(){
    fetch("http://localhost:8080/admin/subjects")
    .then(res => res.json())
    .then(data => {

        let rows = data.map(s => `
        <tr>
            <td>${s.subjectId}</td>
            <td>${s.subjectName}</td>
            <td>${s.department?.deptId || ''}</td>
            <td>Sem ${s.semester?.semesterNumber || ''}</td>
            <td>
                <button onclick="deleteSubject('${s.subjectId}')">Delete</button>
            </td>
        </tr>
        `).join("");

        document.getElementById("subjectBody").innerHTML = rows;
    })
}

function deleteSubject(id){
    if(!confirm("Are you sure you want to delete this subject?")) return;

    fetch("http://localhost:8080/admin/subjects/" + id,{ method:"DELETE"})
    .then(res => res.text())
    .then(msg =>{
        alert(msg);
        loadSubjects();
    })
}

//////////////////////////////////////////////////
// DEPARTMENTS
//////////////////////////////////////////////////

function loadDepartments(){
    fetch("http://localhost:8080/admin/departments")
    .then(res => res.json())
    .then(data => {

        let rows = data.map(d => `
        <tr>
            <td>${d.deptId}</td>
            <td>${d.departmentName}</td>
            <td>
                <button onclick="deleteDepartment('${d.deptId}')">Delete</button>
            </td>
        </tr>
        `).join("");

        document.getElementById("deptBody").innerHTML = rows;
    })
}

function deleteDepartment(id){
    if(!confirm("Are you sure you want to delete this department?")) return;

    fetch("http://localhost:8080/admin/departments/" + id,{ method:"DELETE"})
    .then(res => res.text())
    .then(msg =>{
        alert(msg);
        loadDepartments();
    })
}

//////////////////////////////////////////////////
// ASSIGN SUBJECT
//////////////////////////////////////////////////

function loadFacultyDropdown(){
    fetch("http://localhost:8080/admin/faculty")
    .then(res => res.json())
    .then(data => {

        let options = "<option value=''>Select Faculty</option>";

        data.forEach(f => {
            options += `<option value="${f.facultyId}">${f.name}</option>`;
        });

        document.getElementById("facultySelect").innerHTML = options;
    })
}

function loadSubjectDropdown(){
    fetch("http://localhost:8080/admin/subjects")
    .then(res => res.json())
    .then(data => {

        let options = "<option value=''>Select Subject</option>";

        data.forEach(s => {
            options += `<option value="${s.subjectId}">
                ${s.subjectName} (Sem ${s.semester?.semesterNumber || ''})
            </option>`;
        });

        document.getElementById("subjectSelect").innerHTML = options;
    })
}

function assignSubject(){

    let payload = {
        faculty:{ facultyId: document.getElementById("facultySelect").value },
        subject:{ subjectId: document.getElementById("subjectSelect").value }
    };

    fetch("http://localhost:8080/admin/assign",{
        method:"POST",
        headers:{ "Content-Type":"application/json" },
        body: JSON.stringify(payload)
    })
    .then(res => {
        if(res.ok){
            alert("Assigned Successfully");
            loadAssignments();
        }else{
            alert("Error assigning subject");
        }
    })
}

function loadAssignments(){

    fetch("http://localhost:8080/admin/assignments")
    .then(res => res.json())
    .then(data => {

        let rows = data.map(a => `
        <tr>
            <td>${a.faculty?.name || ''}</td>
            <td>${a.subject?.subjectName || ''}</td>
            <td>${a.subject?.department?.deptId || ''}</td>
            <td>Sem ${a.subject?.semester?.semesterNumber || 'N/A'}</td>
        </tr>
        `).join("");

        document.getElementById("assignBody").innerHTML = rows;
    })
}

//////////////////////////////////////////////////
// ATTENDANCE
//////////////////////////////////////////////////

function loadAttendance(){

    fetch("http://localhost:8080/attendance")
    .then(res => res.json())
    .then(data => {

        window.allAttendance = data;
        renderAttendance(data);
    })
}

function renderAttendance(data){

    let rows = data.map(a => `
    <tr>
        <td>${a.student?.studentId || ''}</td>
        <td>${a.subject?.subjectName || ''}</td>
        <td>Sem ${a.subject?.semester?.semesterNumber || ''}</td>
        <td>${a.date}</td>
        <td>${a.status}</td>
    </tr>
    `).join("");

    document.getElementById("attendanceBody").innerHTML = rows;
}

function loadAttendanceFacultyDropdown(){

    fetch("http://localhost:8080/admin/faculty")
    .then(res => res.json())
    .then(data => {

        let options = "<option value=''>Select Faculty</option>";

        data.forEach(f => {
            options += `<option value="${f.facultyId}">${f.name}</option>`;
        });

        document.getElementById("attendanceFaculty").innerHTML = options;
    })
}

function filterAttendance(){

    let facultyId = document.getElementById("attendanceFaculty").value;
    let semId = document.getElementById("attendanceSem").value;

    let filtered = window.allAttendance.filter(a => {

        let fId = a.faculty?.facultyId || "";
        let sId = a.subject?.semester?.semId || "";

        return (facultyId === "" || facultyId === fId) &&
               (semId === "" || semId === sId);
    });

    renderAttendance(filtered);
}

//////////////////////////////////////////////////
// ADD FUNCTIONS (FINAL FIX)
//////////////////////////////////////////////////

function addSubject(){

    let payload = {
        subjectId: document.getElementById("subjectId").value,
        subjectName: document.getElementById("subjectName").value,
        department: {
            deptId: document.getElementById("subjectDept").value
        },
        semester: {
            semId: document.getElementById("subjectSem").value
        }
    };

    fetch("http://localhost:8080/admin/subjects",{
        method:"POST",
        headers:{ "Content-Type":"application/json" },
        body: JSON.stringify(payload)
    })
    .then(res => {
        if(res.ok){
            alert("Subject Added Successfully");
            loadSubjects();
        }else{
            alert("Error adding subject");
        }
    });
}

function addStudent(){

    let payload = {
        studentId: document.getElementById("studentId").value,
        name: document.getElementById("studentName").value,
        email: document.getElementById("studentEmail").value,
        department: {
            deptId: document.getElementById("studentDept").value
        },
        semester: {
            semId: document.getElementById("studentSem").value
        }
    };

    fetch("http://localhost:8080/admin/students",{
        method:"POST",
        headers:{ "Content-Type":"application/json" },
        body: JSON.stringify(payload)
    })
    .then(res => {
        if(res.ok){
            alert("Student Added Successfully");
            loadStudents();
        }else{
            alert("Error adding student");
        }
    });
}

function addDepartment(){

    let payload = {
        deptId: document.getElementById("deptId").value,
        departmentName: document.getElementById("deptName").value
    };

    fetch("http://localhost:8080/admin/departments",{
        method:"POST",
        headers:{ "Content-Type":"application/json" },
        body: JSON.stringify(payload)
    })
    .then(res => {
        if(res.ok){
            alert("Department Added Successfully");
            loadDepartments();
        }else{
            alert("Error adding department");
        }
    });
}

function addFaculty(){

    let payload = {
        facultyId: document.getElementById("facultyId").value,
        name: document.getElementById("facultyName").value,
        email: document.getElementById("facultyEmail").value,
        password: document.getElementById("facultyPassword").value,
        department: {
            deptId: document.getElementById("facultyDept").value
        }
    };

    fetch("http://localhost:8080/admin/faculty",{
        method:"POST",
        headers:{ "Content-Type":"application/json" },
        body: JSON.stringify(payload)
    })
    .then(res => {
        if(res.ok){
            alert("Faculty Added Successfully");
            loadFaculty();
        }else{
            alert("Error adding faculty");
        }
    });
}