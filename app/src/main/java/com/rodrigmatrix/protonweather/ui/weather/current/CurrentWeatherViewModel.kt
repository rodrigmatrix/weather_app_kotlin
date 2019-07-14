package com.rodrigmatrix.protonweather.ui.weather.current

import androidx.lifecycle.ViewModel
import com.rodrigmatrix.protonweather.data.provider.UnitProvider
import com.rodrigmatrix.protonweather.data.repository.ForecastRepository
import com.rodrigmatrix.protonweather.internal.UnitSystem
import com.rodrigmatrix.protonweather.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    private val unitProvider: UnitProvider
) : ViewModel() {
    private val unitSystem = unitProvider.getUnitSystem()
    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC
    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather(isMetric)
    }

    val weatherLocation by lazyDeferred {
        forecastRepository.getWeatherLocation()
    }
}
