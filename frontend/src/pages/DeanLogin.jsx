import { useState } from "react";

function DeanLogin() {

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = () => {

    console.log(username);
    console.log(password);

    alert("Dean Login");
  };

  return (

    <div className="h-screen flex items-center justify-center bg-gradient-to-br from-gray-950 via-slate-900 to-red-950">

      <div className="backdrop-blur-lg bg-white/10 border border-white/20 p-10 rounded-3xl shadow-2xl w-[400px]">

        <h1 className="text-4xl font-bold text-white text-center mb-2">
          DEAN PORTAL
        </h1>

        <p className="text-gray-300 text-center mb-8">
          Analytics & Department Reports
        </p>

        <input
          type="text"
          placeholder="Dean Username"
          className="w-full p-4 rounded-xl bg-white/20 text-white placeholder-gray-300 outline-none mb-5"
          onChange={(e) => setUsername(e.target.value)}
        />

        <input
          type="password"
          placeholder="Dean Password"
          className="w-full p-4 rounded-xl bg-white/20 text-white placeholder-gray-300 outline-none mb-6"
          onChange={(e) => setPassword(e.target.value)}
        />

        <button
          onClick={handleLogin}
          className="w-full bg-red-600 hover:bg-red-700 transition duration-300 text-white font-semibold p-4 rounded-xl shadow-lg"
        >
          LOGIN
        </button>

      </div>

    </div>
  );
}

export default DeanLogin;