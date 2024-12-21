package ru.alphadrow.gb.myweather.repository

import ru.alphadrow.gb.myweather.domain.Weather

interface Repository {
    fun getWeatherFromRemoteSource():Weather
    fun getWeatherFromLocalSource():Weather

}