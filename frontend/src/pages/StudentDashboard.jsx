import DashboardLayout from "../layouts/DashboardLayout";
import StatsCard from "../components/StatsCard";

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

      {/* TEMPORARY DASHBOARD CONTENT */}

      <div className="bg-slate-900 rounded-3xl p-10 shadow-xl">

        <h1 className="text-4xl font-bold text-white mb-4">
          Dashboard Working
        </h1>

        <p className="text-slate-400 text-lg">
          Student dashboard is successfully connected.
        </p>

      </div>

    </DashboardLayout>
  );
}

export default StudentDashboard;