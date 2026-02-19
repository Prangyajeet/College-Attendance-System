async function login() {

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    const response = await fetch("/auth/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            email: email,
            password: password
        })
    });

    if (response.ok) {
        window.location.href = "dashboard.html";
    } else {
        document.getElementById("message").innerText = "Login Failed";
    }
}


async function markAttendance() {

    const studentId = document.getElementById("studentId").value;
    const subjectId = document.getElementById("subjectId").value;
    const status = document.getElementById("status").value;

    const response = await fetch("/attendance", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            date: new Date().toISOString().split('T')[0],
            status: status,
            student: {
                id: studentId
            },
            subject: {
                id: subjectId
            }
        })
    });

    if (response.ok) {
        document.getElementById("result").innerText = "Attendance Marked Successfully!";
    } else {
        document.getElementById("result").innerText = "Error marking attendance.";
    }
}
