package com.rodrigmatrix.protonweather.data.network

import androidx.lifecycle.LiveData
import com.rodrigmatrix.proton.CurrentWeatherResponse

interface WeatherNetworkDataSource {
    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(
        location: String,
        languageCode: String
    )
}