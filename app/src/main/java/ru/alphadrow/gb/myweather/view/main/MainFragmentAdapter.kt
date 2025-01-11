package ru.alphadrow.gb.myweather.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.alphadrow.gb.myweather.R
import ru.alphadrow.gb.myweather.domain.Weather
import ru.alphadrow.gb.myweather.view.OnItemViewClickListener

class MainFragmentAdapter: RecyclerView.Adapter<MainFragmentAdapter.MainFragmentViewHolder>() {

    private var weatherData:List<Weather> = listOf()
    private lateinit var listener: OnItemViewClickListener

    fun setWeather(data:List<Weather>){
        weatherData = data
        notifyDataSetChanged()
    }
    fun setOnItemClickListener(onItemViewClickListener: OnItemViewClickListener){
        listener = onItemViewClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainFragmentViewHolder {
        val holder = MainFragmentViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_main_recycler_item, parent, false))
        return holder
    }

    override fun getItemCount(): Int {
        return weatherData.size
    }

    override fun onBindViewHolder(holder: MainFragmentViewHolder, position: Int) {
        holder.render(weatherData[position])
    }

    inner class MainFragmentViewHolder(view: View):RecyclerView.ViewHolder(view){ //inner class ??
        fun render(weather: Weather){
            itemView.findViewById<TextView>(R.id.mainFragmentRecyclerItemTextView)
                .text = weather.city.name
            itemView.setOnClickListener(object : OnClickListener {
                override fun onClick(v: View?) {
                    listener.onItemClick(weather)
                }

            })
        }
    }
}