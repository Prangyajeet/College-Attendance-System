import DashboardLayout from "../layouts/DashboardLayout";
import StatsCard from "../components/StatsCard";
import AttendanceChart from "../components/AttendanceChart";
import AttendanceTable from "../components/AttendanceTable";

function StudentDashboard() {

  return (

    <DashboardLayout>

      {/* STATS SECTION */}

      <div className="grid grid-cols-4 gap-6 mb-8">

        <StatsCard
          title="Attendance %"
          value="92%"
          color="from-cyan-500 to-blue-600"
        />

        <StatsCard
          title="Present Classes"
          value="120"
          color="from-emerald-500 to-green-600"
        />

        <StatsCard
          title="Absent Classes"
          value="08"
          color="from-red-500 to-pink-600"
        />

        <StatsCard
          title="Total Subjects"
          value="06"
          color="from-purple-500 to-indigo-600"
        />

      </div>

      {/* ATTENDANCE CHART */}

      <div className="mb-8">

        <AttendanceChart />

      </div>

      {/* ATTENDANCE TABLE */}

      <AttendanceTable />

    </DashboardLayout>
  );
}

export default StudentDashboard;