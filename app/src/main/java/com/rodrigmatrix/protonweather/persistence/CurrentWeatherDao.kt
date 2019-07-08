package com.rodrigmatrix.protonweather.persistence

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rodrigmatrix.protonweather.persistence.entity.CURRENT_WEATHER_ID
import com.rodrigmatrix.protonweather.persistence.entity.Current
import com.rodrigmatrix.protonweather.persistence.unlocalized.ImperialCurrentWeatherEntry
import com.rodrigmatrix.protonweather.persistence.unlocalized.MetricCurrentWeatherEntry

interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry: Current)

    @Query("SELECT * FROM current_weather WHERE id = $CURRENT_WEATHER_ID")
    fun getWeatherMetric(): LiveData<MetricCurrentWeatherEntry>

    @Query("SELECT * FROM current_weather WHERE id = $CURRENT_WEATHER_ID")
    fun getWeatherImperial(): LiveData<ImperialCurrentWeatherEntry>
}