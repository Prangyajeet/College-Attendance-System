function login(){

let email = document.getElementById("email").value;
let password = document.getElementById("password").value;

fetch("http://localhost:8080/auth/login",{

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

if(data.role==="ADMIN"){

localStorage.setItem("loggedIn","true");
localStorage.setItem("role","ADMIN");

window.location.href="admin-dashboard.html";

}

else if(data.role==="FACULTY"){

localStorage.setItem("loggedIn","true");
localStorage.setItem("role","FACULTY");

/* STORE FACULTY DATA */
localStorage.setItem("facultyId", data.facultyId);
localStorage.setItem("facultyName", data.name);
localStorage.setItem("facultyDept", data.department);

window.location.href="dashboard.html";

}

/* ✅ NEW: DEAN LOGIN */
else if(data.role==="DEAN"){

localStorage.setItem("loggedIn","true");
localStorage.setItem("role","DEAN");

window.location.href="dean-dashboard.html";

}

else{

alert("Invalid login");

}

})

.catch(error=>{
alert("Login failed");
});

}