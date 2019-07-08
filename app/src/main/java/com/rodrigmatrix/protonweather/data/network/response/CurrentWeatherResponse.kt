package com.rodrigmatrix.proton

import com.rodrigmatrix.protonweather.persistence.entity.Current
import com.rodrigmatrix.protonweather.persistence.entity.Location


data class CurrentWeatherResponse(
    val current: Current,
    val location: Location
)