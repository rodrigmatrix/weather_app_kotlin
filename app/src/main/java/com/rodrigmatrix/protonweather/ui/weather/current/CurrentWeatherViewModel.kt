package com.rodrigmatrix.protonweather.ui.weather.current

import androidx.lifecycle.ViewModel
import com.rodrigmatrix.protonweather.data.repository.ForecastRepository
import com.rodrigmatrix.protonweather.internal.UnitSystem
import com.rodrigmatrix.protonweather.internal.lazyDefered

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository
) : ViewModel() {
    private val unitSystem = UnitSystem.METRIC
    private val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC
    val weather by lazyDefered {
        forecastRepository.getCurrentWeather(isMetric)
    }
}
