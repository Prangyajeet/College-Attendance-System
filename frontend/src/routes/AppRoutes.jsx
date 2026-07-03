import { Routes, Route } from "react-router-dom";

import StudentLogin from "../pages/StudentLogin";
import StudentDashboard from "../pages/StudentDashboard";
import AttendancePage from "../pages/AttendancePage";

function AppRoutes() {

  return (

    <Routes>

      <Route
        path="/"
        element={<StudentLogin />}
      />

      <Route
        path="/student-login"
        element={<StudentLogin />}
      />

      <Route
        path="/student-dashboard"
        element={<StudentDashboard />}
      />

      <Route
        path="/attendance"
        element={<AttendancePage />}
      />

    </Routes>
  );
}

export default AppRoutes;