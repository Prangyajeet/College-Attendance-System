const API = "http://localhost:8080";

//////////////////////////////////////////////////////
// SAFE API CALL FUNCTION
//////////////////////////////////////////////////////

async function api(url){
    try{
        const res = await fetch(API + url);
        if(!res.ok){
            console.error("API Error:", res.status);
            return [];
        }
        return await res.json();
    }catch(err){
        console.error("Network Error:", err);
        return [];
    }
}

//////////////////////////////////////////////////////
// PAGE LOAD
//////////////////////////////////////////////////////

window.onload = async function () {

    const name = localStorage.getItem("facultyName");
    const fid = localStorage.getItem("facultyId");
    const dept = localStorage.getItem("facultyDept");

    if (!fid) {
        window.location.href = "login.html";
        return;
    }

    document.getElementById("welcome").innerText = "Welcome, " + name;
    document.getElementById("facultyName").innerText = name;
    document.getElementById("facultyDept").innerText = dept;

    await loadSubjects();
    await loadSchedule();
};

//////////////////////////////////////////////////////
// LOAD SCHEDULE
//////////////////////////////////////////////////////

async function loadSchedule() {

    const fid = localStorage.getItem("facultyId");
    const tbody = document.getElementById("scheduleTable");

    tbody.innerHTML = `<tr><td colspan="5">Loading...</td></tr>`;

    const data = await api(`/schedule/faculty/${fid}`);

    if (!data || data.length === 0) {
        tbody.innerHTML = `<tr><td colspan="5">No Schedule Found</td></tr>`;
        return;
    }

    tbody.innerHTML = data.map(s => `
        <tr>
            <td>${s.classDate}</td>
            <td>
                ${s.subject?.subjectName || ''}
                <br><small>Sem ${s.subject?.semester?.semesterNumber || ''}</small>
            </td>
            <td>${s.startTime}</td>
            <td>${s.endTime}</td>
            <td>${s.totalHours}</td>
        </tr>
    `).join("");
}

//////////////////////////////////////////////////////
// LOAD SUBJECTS (FIXED)
//////////////////////////////////////////////////////

async function loadSubjects() {

    const fid = localStorage.getItem("facultyId");
    const dropdown = document.getElementById("subject");

    dropdown.innerHTML = `<option>Loading subjects...</option>`;

    const data = await api(`/subjects/faculty/${fid}`);

    console.log("Subjects:", data); // debug

    if (!data || data.length === 0) {
        dropdown.innerHTML = `<option>No Subjects Assigned</option>`;
        return;
    }

    dropdown.innerHTML = data.map(s => `
        <option value="${s.subjectId}">
            ${s.subjectName} ${s.semester ? `(Sem ${s.semester.semesterNumber})` : ""}
        </option>
    `).join("");
}

//////////////////////////////////////////////////////
// LOAD STUDENTS
//////////////////////////////////////////////////////

async function loadStudents() {

    const subjectId = document.getElementById("subject").value;
    if (!subjectId) return;

    const tbody = document.getElementById("students");
    tbody.innerHTML = `<tr><td colspan="3">Loading students...</td></tr>`;

    const students = await api(`/students/subject/${subjectId}`);

    if (!students || students.length === 0) {
        tbody.innerHTML = `<tr><td colspan="3">No Students Found</td></tr>`;
        return;
    }

    tbody.innerHTML = students.map(st => `
        <tr>
            <td>${st.studentId}</td>
            <td>${st.name}</td>
            <td>
                <label class="switch">
                    <input type="checkbox" class="att" value="${st.studentId}" checked>
                    <span class="slider"></span>
                </label>
            </td>
        </tr>
    `).join("");
}

//////////////////////////////////////////////////////
// SUBMIT ATTENDANCE
//////////////////////////////////////////////////////

async function submitAttendance() {

    const subjectId = document.getElementById("subject").value;
    const fid = localStorage.getItem("facultyId");
    const checks = document.querySelectorAll(".att");

    let present = 0;
    let total = checks.length;
    let today = new Date().toISOString().split("T")[0];

    for (const c of checks) {

        if (c.checked) present++;

        await fetch(`${API}/attendance?facultyId=${fid}`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                date: today,
                status: c.checked ? "PRESENT" : "ABSENT",
                student: { studentId: c.value },
                subject: { subjectId: subjectId }
            })
        });
    }

    let percent = total === 0 ? 0 : Math.round((present / total) * 100);

    document.getElementById("percent").innerText = percent + "%";
    document.getElementById("counts").innerText =
        `Present: ${present} | Absent: ${total - present}`;

    alert("Attendance Submitted Successfully");
}

//////////////////////////////////////////////////////
// TOGGLE
//////////////////////////////////////////////////////

function toggleSchedule(){
    const box = document.getElementById("scheduleBox");
    box.style.display = box.style.display === "none" ? "table" : "none";
}

function toggleStudents(){
    const box = document.getElementById("attendanceBox");
    box.style.display = box.style.display === "none" ? "block" : "none";
}

//////////////////////////////////////////////////////
// RULE POPUP
//////////////////////////////////////////////////////

function openRules(){
    document.getElementById("rulesModal").style.display = "flex";
}

function closeRules(){
    document.getElementById("rulesModal").style.display = "none";
}

//////////////////////////////////////////////////////
// LOGOUT
//////////////////////////////////////////////////////

function logout(){
    localStorage.clear();
    window.location.href = "login.html";
}