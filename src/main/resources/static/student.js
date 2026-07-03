const API = "http://localhost:8080";

window.onload = function(){
    loadStudent();
}

//////////////////////////////////////////////////
// LOAD STUDENT INFO + DATA
//////////////////////////////////////////////////

async function loadStudent(){

    const studentId = localStorage.getItem("studentId");
    const name = localStorage.getItem("studentName");
    let dept = localStorage.getItem("studentDept"); // 🔥 we will convert this
    const sem = localStorage.getItem("studentSem");

    if(!studentId){
        window.location.href = "student-login.html";
        return;
    }

    // ✅ FIX: Convert Dept Code → Name
    const deptMap = {
        "D01": "CSE",
        "D02": "ECE",
        "D03": "ME",
        "D04": "CE"
    };

    const deptName = deptMap[dept] || dept; // fallback if not found

    // Header
    document.getElementById("studentName").innerText = name;
    document.getElementById("studentInfo").innerText =
        `Dept: ${deptName} | Sem ${sem.replace("S","")}`;

    // Load attendance
    loadAttendance(studentId);
}

//////////////////////////////////////////////////
// LOAD ATTENDANCE
//////////////////////////////////////////////////

async function loadAttendance(studentId){

    try{

        const res = await fetch(`${API}/attendance/student/${studentId}`);

        if(!res.ok){
            throw new Error("API Error");
        }

        const data = await res.json();

        if(!Array.isArray(data) || data.length === 0){
            document.getElementById("recordTable").innerHTML =
            `<tr><td colspan="4">No Records</td></tr>`;

            document.getElementById("subjectTable").innerHTML =
            `<tr><td colspan="5">No Data</td></tr>`;

            return;
        }

        renderRecords(data);
        calculateSummary(data);
        renderSubjectWise(data);
        renderChart(data);

    }catch(err){
        console.error(err);
    }
}

//////////////////////////////////////////////////
// RECENT RECORDS
//////////////////////////////////////////////////

function renderRecords(data){

    let rows = data.slice(-10).reverse().map(a => {

        let statusClass = a.status === "PRESENT" ? "present" : "absent";

        return `
        <tr>
            <td>${a.date}</td>
            <td>${a.subject?.subjectName || ''}</td>
            <td>Sem ${a.subject?.semester?.semesterNumber || ''}</td>
            <td class="${statusClass}">${a.status}</td>
        </tr>
        `;
    }).join("");

    document.getElementById("recordTable").innerHTML = rows;
}

//////////////////////////////////////////////////
// SUMMARY
//////////////////////////////////////////////////

function calculateSummary(data){

    let present = data.filter(a => a.status === "PRESENT").length;
    let total = data.length;
    let percent = total === 0 ? 0 : Math.round((present/total)*100);

    document.getElementById("percentage").innerText = percent + "%";
    document.getElementById("counts").innerText =
        `Present: ${present} | Absent: ${total - present}`;

    let color = percent >= 75 ? "#16a34a" : "#dc2626";
    document.getElementById("percentage").style.color = color;
}

//////////////////////////////////////////////////
// SUBJECT-WISE
//////////////////////////////////////////////////

function renderSubjectWise(data){

    let map = {};

    data.forEach(a => {

        let sub = a.subject?.subjectName || "Unknown";

        if(!map[sub]){
            map[sub] = {
                total:0,
                present:0,
                sem: a.subject?.semester?.semesterNumber || ''
            };
        }

        map[sub].total++;

        if(a.status === "PRESENT"){
            map[sub].present++;
        }
    });

    let rows = Object.keys(map).map(sub => {

        let total = map[sub].total;
        let present = map[sub].present;
        let percent = Math.round((present/total)*100);

        let color = percent >= 75 ? "#16a34a" : "#dc2626";

        return `
        <tr>
            <td>${sub}</td>
            <td>Sem ${map[sub].sem}</td>
            <td>${total}</td>
            <td>${present}</td>
            <td style="color:${color}; font-weight:600">${percent}%</td>
        </tr>
        `;
    }).join("");

    document.getElementById("subjectTable").innerHTML = rows;
}

//////////////////////////////////////////////////
// GRAPH
//////////////////////////////////////////////////

let chartInstance = null;

function renderChart(data){

    let present = data.filter(a => a.status === "PRESENT").length;
    let absent = data.length - present;

    const ctx = document.getElementById('attendanceChart');

    if(chartInstance){
        chartInstance.destroy();
    }

    chartInstance = new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: ['Present', 'Absent'],
            datasets: [{
                data: [present, absent]
            }]
        }
    });
}

//////////////////////////////////////////////////
// LOGOUT
//////////////////////////////////////////////////

function logout(){
    localStorage.clear();
    window.location.href="student-login.html";
}