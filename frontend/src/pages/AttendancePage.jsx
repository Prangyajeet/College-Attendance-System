import DashboardLayout from "../layouts/DashboardLayout";

function AttendancePage() {

  const attendanceData = [

    {
      subject: "Java",
      attended: 28,
      total: 30,
      percentage: "93%",
    },

    {
      subject: "DBMS",
      attended: 25,
      total: 30,
      percentage: "83%",
    },

    {
      subject: "Python",
      attended: 27,
      total: 30,
      percentage: "90%",
    },

    {
      subject: "Operating System",
      attended: 26,
      total: 30,
      percentage: "86%",
    },

  ];

  return (

    <DashboardLayout>

      {/* PAGE TITLE */}

      <div className="mb-8">

        <h1 className="text-4xl font-bold text-white mb-2">
          Attendance Details
        </h1>

        <p className="text-slate-400">
          Track your complete attendance records
        </p>

      </div>

      {/* OVERALL ATTENDANCE CARD */}

      <div className="bg-gradient-to-r from-cyan-500 to-blue-600 p-8 rounded-3xl shadow-xl mb-8">

        <h2 className="text-2xl font-bold text-white mb-3">
          Overall Attendance
        </h2>

        <h1 className="text-6xl font-bold text-white">
          92%
        </h1>

        <div className="w-full bg-white/20 h-4 rounded-full mt-6">

          <div className="bg-white h-4 rounded-full w-[92%]"></div>

        </div>

      </div>

      {/* SUBJECT TABLE */}

      <div className="bg-slate-900 p-8 rounded-3xl shadow-xl">

        <h2 className="text-2xl font-bold text-white mb-6">
          Subject-wise Attendance
        </h2>

        <table className="w-full text-left">

          <thead>

            <tr className="border-b border-slate-700 text-slate-400">

              <th className="pb-4">Subject</th>

              <th className="pb-4">Classes Attended</th>

              <th className="pb-4">Total Classes</th>

              <th className="pb-4">Percentage</th>

            </tr>

          </thead>

          <tbody>

            {attendanceData.map((item, index) => (

              <tr
                key={index}
                className="border-b border-slate-800 hover:bg-slate-800 transition"
              >

                <td className="py-5 text-white">
                  {item.subject}
                </td>

                <td className="py-5 text-slate-300">
                  {item.attended}
                </td>

                <td className="py-5 text-slate-300">
                  {item.total}
                </td>

                <td className="py-5">

                  <span className="bg-cyan-500/20 text-cyan-400 px-4 py-2 rounded-xl">
                    {item.percentage}
                  </span>

                </td>

              </tr>

            ))}

          </tbody>

        </table>

      </div>

    </DashboardLayout>
  );
}

export default AttendancePage;