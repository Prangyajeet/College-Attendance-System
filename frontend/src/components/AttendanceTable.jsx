function AttendanceTable() {

  const subjects = [

    {
      subject: "Java",
      faculty: "Dr. Kumar",
      attendance: "95%",
    },

    {
      subject: "DBMS",
      faculty: "Dr. Rao",
      attendance: "88%",
    },

    {
      subject: "Python",
      faculty: "Dr. Das",
      attendance: "92%",
    },

    {
      subject: "Operating System",
      faculty: "Dr. Patel",
      attendance: "90%",
    },

  ];

  return (

    <div className="bg-slate-900 p-6 rounded-3xl shadow-xl mt-8">

      <h1 className="text-2xl font-bold text-white mb-6">
        Subject Attendance
      </h1>

      <table className="w-full text-left">

        <thead>

          <tr className="text-slate-400 border-b border-slate-700">

            <th className="pb-4">Subject</th>

            <th className="pb-4">Faculty</th>

            <th className="pb-4">Attendance</th>

          </tr>

        </thead>

        <tbody>

          {subjects.map((item, index) => (

            <tr
              key={index}
              className="border-b border-slate-800 hover:bg-slate-800 transition"
            >

              <td className="py-4 text-white">
                {item.subject}
              </td>

              <td className="py-4 text-slate-300">
                {item.faculty}
              </td>

              <td className="py-4">

                <span className="bg-cyan-500/20 text-cyan-400 px-4 py-1 rounded-xl">
                  {item.attendance}
                </span>

              </td>

            </tr>

          ))}

        </tbody>

      </table>

    </div>
  );
}

export default AttendanceTable;