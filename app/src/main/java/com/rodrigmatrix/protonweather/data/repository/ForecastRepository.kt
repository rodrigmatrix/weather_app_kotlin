package com.rodrigmatrix.protonweather.data.repository

import androidx.lifecycle.LiveData
import com.rodrigmatrix.protonweather.persistence.entity.WeatherLocation
import com.rodrigmatrix.protonweather.persistence.unlocalized.UnitSpecificCurrentWeatherEntry

interface ForecastRepository {
    suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry>
    suspend fun getWeatherLocation(): LiveData<WeatherLocation>
}