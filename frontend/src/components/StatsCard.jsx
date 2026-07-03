function StatsCard({ title, value, color }) {

  return (

    <div
      className={`p-6 rounded-3xl shadow-xl bg-gradient-to-br ${color}
      hover:scale-105 transition duration-300 cursor-pointer`}
    >

      <h2 className="text-lg text-white/80 mb-2">
        {title}
      </h2>

      <h1 className="text-4xl font-bold text-white">
        {value}
      </h1>

    </div>
  );
}

export default StatsCard;