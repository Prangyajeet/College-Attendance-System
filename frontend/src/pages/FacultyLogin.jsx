import { useState } from "react";

function FacultyLogin() {

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = () => {

    console.log(username);
    console.log(password);

    alert("Faculty Login");
  };

  return (

    <div className="h-screen flex items-center justify-center bg-gradient-to-br from-indigo-800 via-purple-900 to-black">

      <div className="backdrop-blur-lg bg-white/10 border border-white/20 p-10 rounded-3xl shadow-2xl w-[400px]">

        <h1 className="text-4xl font-bold text-white text-center mb-2">
          FACULTY PORTAL
        </h1>

        <p className="text-gray-300 text-center mb-8">
          Manage Attendance & Classes
        </p>

        <input
          type="text"
          placeholder="Faculty Username"
          className="w-full p-4 rounded-xl bg-white/20 text-white placeholder-gray-300 outline-none mb-5"
          onChange={(e) => setUsername(e.target.value)}
        />

        <input
          type="password"
          placeholder="Faculty Password"
          className="w-full p-4 rounded-xl bg-white/20 text-white placeholder-gray-300 outline-none mb-6"
          onChange={(e) => setPassword(e.target.value)}
        />

        <button
          onClick={handleLogin}
          className="w-full bg-purple-600 hover:bg-purple-700 transition duration-300 text-white font-semibold p-4 rounded-xl shadow-lg"
        >
          LOGIN
        </button>

      </div>

    </div>
  );
}

export default FacultyLogin;