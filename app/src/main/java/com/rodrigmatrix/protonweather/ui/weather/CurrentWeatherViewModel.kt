package com.rodrigmatrix.protonweather.ui.weather

import androidx.lifecycle.ViewModel
import com.rodrigmatrix.protonweather.data.repository.ForecastRepository

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository
) : ViewModel() {

}
