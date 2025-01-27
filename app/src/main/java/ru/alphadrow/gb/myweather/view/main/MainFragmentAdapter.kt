package ru.alphadrow.gb.myweather.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.alphadrow.gb.myweather.databinding.ItemFragmentMainRecyclerBinding
import ru.alphadrow.gb.myweather.domain.Weather
import ru.alphadrow.gb.myweather.view.OnItemViewClickListener

class MainFragmentAdapter : RecyclerView.Adapter<MainFragmentAdapter.MainFragmentViewHolder>() {

    private var weatherData: List<Weather> = listOf()
    private lateinit var listener: OnItemViewClickListener

    fun setWeather(data: List<Weather>) {
        weatherData = data
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemViewClickListener: OnItemViewClickListener) {
        listener = onItemViewClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainFragmentViewHolder {
        val holder = ItemFragmentMainRecyclerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MainFragmentViewHolder(holder)
    }

    override fun getItemCount(): Int {
        return weatherData.size
    }

    override fun onBindViewHolder(holder: MainFragmentViewHolder, position: Int) {
        holder.render(weatherData[position])
    }

    inner class MainFragmentViewHolder(private val itemBinding: ItemFragmentMainRecyclerBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun render(weather: Weather) {
            //TODO сделать вместо TextView - EditText и посмотреть что получится
            itemBinding.mainFragmentRecyclerItemTextView.text = weather.city.name
            itemBinding.mainFragmentRecyclerItemTextView.setOnClickListener {
                listener.onItemClick(
                    weather
                )
            }
        }
    }
}