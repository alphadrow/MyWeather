package ru.alphadrow.gb.myweather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.alphadrow.gb.myweather.repository.RepositoryImpl
import java.lang.Thread.sleep
import kotlin.random.Random

class MainViewModel(
    private val LiveDataToObserver: MutableLiveData<AppState> = MutableLiveData(),
    val repositoryImpl: RepositoryImpl = RepositoryImpl()
) :
    ViewModel() {
    fun getLiveData() = LiveDataToObserver

    fun getDataFromRemoteSource() {
        LiveDataToObserver.postValue(AppState.Loading)
        Thread {
            sleep(2000)
            if (Random.nextInt(100) > 50) {
                LiveDataToObserver.postValue(AppState.Success(repositoryImpl.getWeatherFromRemoteSource()))
            } else {
                LiveDataToObserver.postValue(AppState.Error(IllegalStateException()))
            }
        }.start()
    }
}