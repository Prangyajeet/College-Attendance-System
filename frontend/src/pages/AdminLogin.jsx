import { useState } from "react";

function AdminLogin() {

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = () => {

    console.log(username);
    console.log(password);

    alert("Admin Login");
  };

  return (

    <div className="h-screen flex items-center justify-center bg-gradient-to-br from-black via-gray-900 to-blue-950">

      <div className="backdrop-blur-lg bg-white/10 border border-white/20 p-10 rounded-3xl shadow-2xl w-[400px]">

        <h1 className="text-4xl font-bold text-white text-center mb-2">
          ADMIN PORTAL
        </h1>

        <p className="text-gray-300 text-center mb-8">
          College Attendance Management System
        </p>

        <input
          type="text"
          placeholder="Admin Username"
          className="w-full p-4 rounded-xl bg-white/20 text-white placeholder-gray-300 outline-none mb-5"
          onChange={(e) => setUsername(e.target.value)}
        />

        <input
          type="password"
          placeholder="Admin Password"
          className="w-full p-4 rounded-xl bg-white/20 text-white placeholder-gray-300 outline-none mb-6"
          onChange={(e) => setPassword(e.target.value)}
        />

        <button
          onClick={handleLogin}
          className="w-full bg-blue-600 hover:bg-blue-700 transition duration-300 text-white font-semibold p-4 rounded-xl shadow-lg"
        >
          LOGIN
        </button>

      </div>

    </div>
  );
}

export default AdminLogin;