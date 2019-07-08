package com.rodrigmatrix.protonweather.ui.weather

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.rodrigmatrix.protonweather.R
import com.rodrigmatrix.protonweather.data.ApixuWeatherApi
import com.rodrigmatrix.protonweather.data.network.ConnectivityInterceptor
import com.rodrigmatrix.protonweather.data.network.ConnectivityInterceptorImpl
import com.rodrigmatrix.protonweather.data.network.WeatherNetworkDataSource
import com.rodrigmatrix.protonweather.data.network.WeatherNetworkDataSourceImpl
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.await

class CurrentWeatherFragment : Fragment() {

    companion object {
        fun newInstance() = CurrentWeatherFragment()
    }

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CurrentWeatherViewModel::class.java)
        val apiService = ApixuWeatherApi(ConnectivityInterceptorImpl(this.context!!))
        val weatherNetworkDataSource = WeatherNetworkDataSourceImpl(apiService)
        weatherNetworkDataSource.downloadedCurrentWeather.observe(this, Observer {
            text.text = it.toString()
        })
        GlobalScope.launch(Dispatchers.Main) {
            weatherNetworkDataSource.fetchCurrentWeather("London", "en")
        }

    }

}
