package com.rodrigmatrix.protonweather.data.provider

import com.rodrigmatrix.protonweather.persistence.entity.WeatherLocation

interface LocationProvider {
    suspend fun hasLocationChanged(lastWeatherLocation: WeatherLocation): Boolean
    suspend fun getPreferredLocationString(): String
}