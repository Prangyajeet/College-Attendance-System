import { useState } from "react";
import { useNavigate } from "react-router-dom";

import bgImage from "../assets/student-bg.jpg";

function StudentLogin() {

  const navigate = useNavigate();

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = () => {

    console.log(username);
    console.log(password);

    navigate("/student-dashboard");
  };

  return (

    <div className="h-screen flex overflow-hidden">

      {/* LEFT SIDE */}

      <div
        className="w-2/3 bg-cover bg-center relative"
        style={{
          backgroundImage: `url(${bgImage})`,
        }}
      >

        {/* OVERLAY */}

        <div className="absolute inset-0 bg-black/60"></div>

        {/* TEXT CONTENT */}

        <div className="relative z-10 flex flex-col justify-center h-full px-20 text-white">

          <h1 className="text-6xl font-bold leading-tight mb-6">
            Smart College
            <br />
            Attendance System
          </h1>

          <p className="text-xl text-gray-300 max-w-2xl leading-relaxed">
            Empowering students with real-time attendance tracking,
            smart analytics, reports, and modern educational management.
          </p>

        </div>

      </div>

      {/* RIGHT SIDE */}

      <div className="w-1/3 bg-slate-950 flex items-center justify-center">

        <div className="w-[420px] p-10">

          <h1 className="text-4xl font-bold text-white text-center mb-2">
            STUDENT LOGIN
          </h1>

          <p className="text-slate-400 text-center mb-10">
            Welcome back to your portal
          </p>

          <input
            type="text"
            placeholder="Enter Username"
            className="w-full p-4 mb-5 rounded-2xl bg-slate-900 border border-slate-700 text-white outline-none focus:border-cyan-500"
            onChange={(e) => setUsername(e.target.value)}
          />

          <input
            type="password"
            placeholder="Enter Password"
            className="w-full p-4 mb-7 rounded-2xl bg-slate-900 border border-slate-700 text-white outline-none focus:border-cyan-500"
            onChange={(e) => setPassword(e.target.value)}
          />

          <button
            onClick={handleLogin}
            className="w-full bg-cyan-500 hover:bg-cyan-600 transition duration-300 p-4 rounded-2xl text-white font-bold text-lg shadow-lg"
          >
            LOGIN
          </button>

          <p className="text-slate-500 text-center mt-8 text-sm">
            College Attendance Management System
          </p>

        </div>

      </div>

    </div>
  );
}

export default StudentLogin;