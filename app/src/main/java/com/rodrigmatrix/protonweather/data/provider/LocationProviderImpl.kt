package com.rodrigmatrix.protonweather.data.provider

import com.rodrigmatrix.protonweather.persistence.entity.WeatherLocation

class LocationProviderImpl : LocationProvider {
    override suspend fun hasLocationChanged(lastWeatherLocation: WeatherLocation): Boolean {
        return true
    }

    override suspend fun getPreferredLocationString(): String {
        return "Caxias do Sul"
    }
}