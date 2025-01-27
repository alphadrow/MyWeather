package ru.alphadrow.gb.myweather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.alphadrow.gb.myweather.repository.Repository
import ru.alphadrow.gb.myweather.repository.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(
    private val LiveDataToObserver: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: Repository = RepositoryImpl()
) :
    ViewModel() {
    fun getLiveData() = LiveDataToObserver

    fun getWeatherFromLocalSourceWorld() {
        getDataFromLocalSource(false)
    }

    fun getWeatherFromLocalSourceRus() {
        getDataFromLocalSource(true)
    }

    fun getDataFromLocalSource(isRussian: Boolean) {
        LiveDataToObserver.postValue(AppState.Loading)
        Thread {
            sleep(1000)
            getWeather(isRussian)
        }.start()
    }

    private fun getWeather(isRussian: Boolean) {
        if (isRussian) {
            LiveDataToObserver.postValue(AppState.Success(repositoryImpl.getWeatherFromLocalStorageRus()))
        } else {
            LiveDataToObserver.postValue(AppState.Success(repositoryImpl.getWeatherFromLocalStorageWorld()))
        }
    }
}