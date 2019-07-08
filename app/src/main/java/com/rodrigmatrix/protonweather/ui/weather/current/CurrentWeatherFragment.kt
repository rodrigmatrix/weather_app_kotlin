package com.rodrigmatrix.protonweather.ui.weather.current

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

import com.rodrigmatrix.protonweather.R
import com.rodrigmatrix.protonweather.data.ApixuWeatherApi
import com.rodrigmatrix.protonweather.data.network.ConnectivityInterceptorImpl
import com.rodrigmatrix.protonweather.data.network.WeatherNetworkDataSourceImpl
import com.rodrigmatrix.protonweather.internal.glide.GlideApp
import com.rodrigmatrix.protonweather.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class CurrentWeatherFragment : ScopedFragment(), KodeinAware {

    companion object {
        fun newInstance() =
            CurrentWeatherFragment()
    }

    override val kodein by closestKodein()
    private val viewModelFactory: CurrentWeatherViewModelFactory by instance()

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CurrentWeatherViewModel::class.java)
        bindUI()
    }
    private fun bindUI() = launch{
        val currentWeather = viewModel.weather.await()
        currentWeather.observe(this@CurrentWeatherFragment, Observer {
            if(it == null) return@Observer
            group_loading.visibility = View.GONE
            updateLocation("Caxias do Sul")
            updateDateToToday()
            updateTemperatures(it.temperature, it.feelsLikeTemperature)
            updateCondition(it.conditionText)
            updatePrecipitation(it.precipitationVolume)
            updateWind(it.windDirection, it.windSpeed)
            updateVisibility(it.visibilityDistance)

            GlideApp.with(this@CurrentWeatherFragment)
                .load("https:${it.conditionIconUrl}")
                .into(imageView_condition_icon)
        })
    }

    private fun updateDateToToday(){
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Today"
    }

    private fun chooseLocalizedUnitAbbreviation(metric: String, imperial: String): String{
        return if(viewModel.isMetric) metric else imperial
    }

    private fun updateLocation(location: String){
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = location
    }

    @SuppressLint("SetTextI18n")
    private fun updateTemperatures(temperature: Double, fellsLike: Double){
        val unit = chooseLocalizedUnitAbbreviation("°C", "°F")
        textView_temperature.text = "$temperature $unit"
        textView_feels_like_temperature.text = "$fellsLike $unit"
    }

    private fun updateCondition(condition: String){
        textView_condition.text = condition
    }

    @SuppressLint("SetTextI18n")
    private fun updatePrecipitation(precipitationVolume: Double){
        val unit = chooseLocalizedUnitAbbreviation("mm", "in")
        textView_precipitation.text = "Precipitation: $precipitationVolume $unit"
    }

    @SuppressLint("SetTextI18n")
    private fun updateWind(windDirection: String, windSpeed: Double){
        val unit = chooseLocalizedUnitAbbreviation("kph", "mph")
        textView_precipitation.text = "Wind: $windDirection $windSpeed $unit"
    }

    @SuppressLint("SetTextI18n")
    private fun updateVisibility(visibilityDistance: Double){
        val unit = chooseLocalizedUnitAbbreviation("km", "mi.")
        textView_visibility.text = "Visibility: $visibilityDistance $unit"
    }

}
