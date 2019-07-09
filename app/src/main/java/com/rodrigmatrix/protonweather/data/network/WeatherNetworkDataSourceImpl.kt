package com.rodrigmatrix.protonweather.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rodrigmatrix.protonweather.data.ApixuWeatherApi
import com.rodrigmatrix.protonweather.data.network.response.CurrentWeatherResponse
import com.rodrigmatrix.protonweather.internal.NoConnectivityException

class WeatherNetworkDataSourceImpl(
    private val apixuWeatherApi: ApixuWeatherApi
): WeatherNetworkDataSource {
    private val _downloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()
    override val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadedCurrentWeather
    override suspend fun fetchCurrentWeather(location: String, languageCode: String) {
        try {
            val fetchedCurrentWeather = apixuWeatherApi
                .getCurrentWeather(location, languageCode)
            _downloadedCurrentWeather.postValue(fetchedCurrentWeather)
        }
        catch (e: NoConnectivityException){
            Log.e("Connectivity", "No internet Connection.", e)
        }
    }
}