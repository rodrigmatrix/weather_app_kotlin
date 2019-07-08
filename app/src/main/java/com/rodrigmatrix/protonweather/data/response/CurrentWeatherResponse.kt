package com.rodrigmatrix.proton

import com.rodrigmatrix.protonweather.data.response.Current
import com.rodrigmatrix.protonweather.data.response.Location


data class CurrentWeatherResponse(
    val current: Current,
    val location: Location
)