package ru.alphadrow.gb.myweather.repository

import ru.alphadrow.gb.myweather.domain.Weather
import ru.alphadrow.gb.myweather.domain.getRussianCities
import ru.alphadrow.gb.myweather.domain.getWorldCities

class RepositoryImpl:Repository {
    override fun getWeatherFromRemoteSource(): Weather {
        return Weather()
    }

    override fun getWeatherFromLocalSource(): Weather {
        return Weather()
    }

    override fun getWeatherFromLocalStorageRus(): List<Weather> {
        return getRussianCities()
    }

    override fun getWeatherFromLocalStorageWorld(): List<Weather> {
        return  getWorldCities()
    }

}