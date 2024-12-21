package ru.alphadrow.gb.myweather.repository

import ru.alphadrow.gb.myweather.domain.Weather

class RepositoryImpl:Repository {
    override fun getWeatherFromRemoteSource(): Weather {
        return Weather()
    }

    override fun getWeatherFromLocalSource(): Weather {
        return Weather()
    }

}