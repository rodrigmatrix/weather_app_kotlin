package com.rodrigmatrix.protonweather

import android.app.Application
import androidx.preference.PreferenceManager
import com.jakewharton.threetenabp.AndroidThreeTen
import com.rodrigmatrix.protonweather.data.ApixuWeatherApi
import com.rodrigmatrix.protonweather.data.network.ConnectivityInterceptor
import com.rodrigmatrix.protonweather.data.network.ConnectivityInterceptorImpl
import com.rodrigmatrix.protonweather.data.network.WeatherNetworkDataSource
import com.rodrigmatrix.protonweather.data.network.WeatherNetworkDataSourceImpl
import com.rodrigmatrix.protonweather.data.provider.LocationProvider
import com.rodrigmatrix.protonweather.data.provider.LocationProviderImpl
import com.rodrigmatrix.protonweather.data.provider.UnitProvider
import com.rodrigmatrix.protonweather.data.provider.UnitProviderImpl
import com.rodrigmatrix.protonweather.data.repository.ForecastRepository
import com.rodrigmatrix.protonweather.data.repository.ForecastRepositoryImpl
import com.rodrigmatrix.protonweather.persistence.ForecastDatabase
import com.rodrigmatrix.protonweather.ui.weather.current.CurrentWeatherViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ForecastApplication: Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ForecastApplication))
        bind() from singleton { ForecastDatabase(instance()) }
        bind() from singleton { instance<ForecastDatabase>().currentWeatherDao() }
        bind() from singleton { instance<ForecastDatabase>().weatherLocationDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { ApixuWeatherApi(instance()) }
        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind<LocationProvider>() with singleton { LocationProviderImpl() }
        bind<ForecastRepository>() with  singleton { ForecastRepositoryImpl(instance(), instance(), instance(), instance()) }
        bind<UnitProvider>() with singleton { UnitProviderImpl(instance()) }
        bind() from provider { CurrentWeatherViewModelFactory(instance(), instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
    }
}