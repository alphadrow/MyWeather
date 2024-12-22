package ru.alphadrow.gb.myweather.domain

data class Weather(val city:City=City("Москва", 55.0, 37.0),
                    val temperature: Int = -1,
                    val feelsLike:Int = -5
)
