import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  Tooltip,
  ResponsiveContainer,
} from "recharts";

function AttendanceChart() {

  const data = [
    { month: "Jan", attendance: 85 },
    { month: "Feb", attendance: 88 },
    { month: "Mar", attendance: 90 },
    { month: "Apr", attendance: 92 },
    { month: "May", attendance: 95 },
    { month: "Jun", attendance: 93 },
  ];

  return (

    <div className="bg-slate-900 p-6 rounded-3xl shadow-xl">

      <h1 className="text-2xl font-bold text-white mb-6">
        Attendance Overview
      </h1>

      <div className="h-80">

        <ResponsiveContainer width="100%" height="100%">

          <LineChart data={data}>

            <XAxis dataKey="month" stroke="#94a3b8" />

            <YAxis stroke="#94a3b8" />

            <Tooltip />

            <Line
              type="monotone"
              dataKey="attendance"
              stroke="#06b6d4"
              strokeWidth={4}
            />

          </LineChart>

        </ResponsiveContainer>

      </div>

    </div>
  );
}

export default AttendanceChart;