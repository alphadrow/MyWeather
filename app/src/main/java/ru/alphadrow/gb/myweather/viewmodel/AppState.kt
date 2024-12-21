package ru.alphadrow.gb.myweather.viewmodel

import ru.alphadrow.gb.myweather.domain.Weather

sealed class AppState {
    object Loading:AppState()
    data class Success(val weatherData:Weather):AppState()
    data class Error(val error:Throwable):AppState()
}