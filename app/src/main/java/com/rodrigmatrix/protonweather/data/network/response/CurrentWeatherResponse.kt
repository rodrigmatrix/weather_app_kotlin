package com.rodrigmatrix.protonweather.data.network.response

import com.rodrigmatrix.protonweather.persistence.entity.Current
import com.rodrigmatrix.protonweather.persistence.entity.WeatherLocation


data class CurrentWeatherResponse(
    val current: Current,
    val location: WeatherLocation
)