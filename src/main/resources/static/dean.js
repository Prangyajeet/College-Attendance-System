const API = "http://localhost:8080";

let originalData = []; // store full data

window.onload = function(){

    // LOGIN CHECK
    const role = localStorage.getItem("role");

    if(role !== "DEAN"){
        alert("Unauthorized Access");
        window.location.href = "login.html";
        return;
    }

    loadData();
}

function loadData(){

fetch(`${API}/dean/report`)
.then(res => {
    if(!res.ok) throw new Error("API Error");
    return res.json();
})
.then(data => {
    originalData = data;
    renderData(data);
})
.catch(err => {
    console.error(err);
    document.getElementById("table").innerHTML =
    `<tr><td colspan="5">Error loading data</td></tr>`;
});

}

// COMMON FUNCTION
function renderData(data){

    if(!data || data.length === 0){
        document.getElementById("table").innerHTML =
        `<tr><td colspan="5">No Data Available</td></tr>`;
        return;
    }

    // STATS
    let total = data.length;

    let avg = data.reduce((sum,d)=>sum + (d.attendancePercentage || 0),0) / total;

    let low = data.filter(d => (d.attendancePercentage || 0) < 75).length;

    if(document.getElementById("total"))
        document.getElementById("total").innerText = total;

    if(document.getElementById("avg"))
        document.getElementById("avg").innerText = avg.toFixed(1) + "%";

    if(document.getElementById("low"))
        document.getElementById("low").innerText = low;

    // TABLE
    let rows = data.map(d => {

        let percent = d.attendancePercentage || 0;
        let color = percent < 75 ? "#dc2626" : "#16a34a";

        return `
        <tr>
            <td>${d.studentName}</td>
            <td>${d.department}</td>
            <td>${d.semester}</td>
            <td>${d.subjectName}</td>
            <td style="color:${color}; font-weight:600">
                ${percent.toFixed(1)}%
            </td>
        </tr>
        `;
    }).join("");

    document.getElementById("table").innerHTML = rows;
}

//////////////////////////////////////////////////
// FILTER FUNCTION
//////////////////////////////////////////////////

function applyFilters(){

    const dept = document.getElementById("department")?.value;
    const sem = document.getElementById("semester")?.value;

    let filtered = originalData.filter(d => {

        let deptMatch = dept ? d.department === dept : true;
        let semMatch = sem ? d.semester == sem : true;

        return deptMatch && semMatch;
    });

    renderData(filtered);
}

//////////////////////////////////////////////////
// LOGOUT
//////////////////////////////////////////////////

function logout(){
    localStorage.clear();
    window.location.href = "login.html";
}