package ru.alphadrow.gb.myweather.view

import ru.alphadrow.gb.myweather.domain.Weather

interface OnItemViewClickListener {
    fun onItemClick(weather: Weather)
}