function login(){

let email = document.getElementById("studentEmail").value;
let password = document.getElementById("studentPassword").value;

fetch("http://localhost:8080/students/login",{

method:"POST",

headers:{
"Content-Type":"application/json"
},

body:JSON.stringify({
email:email,
password:password
})

})

.then(res=>res.json())
.then(data=>{

if(!data){
alert("Invalid login");
return;
}

/* STORE STUDENT DATA */
localStorage.setItem("studentId", data.studentId);
localStorage.setItem("studentName", data.name);
localStorage.setItem("studentDept", data.department.deptId);
localStorage.setItem("studentSem", data.semester.semId);

window.location.href="student.html";

})

.catch(()=>{
alert("Login failed");
});

}