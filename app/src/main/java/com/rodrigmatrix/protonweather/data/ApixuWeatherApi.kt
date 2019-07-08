package com.rodrigmatrix.protonweather.data

import com.google.gson.GsonBuilder
import com.rodrigmatrix.proton.CurrentWeatherResponse
import com.rodrigmatrix.protonweather.data.response.Current
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "ddc9f7a155464d67bea34056190807"

interface ApixuWeatherApi {

    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("q") location: String,
        @Query("lang") languageCode: String = "en"
    ): CurrentWeatherResponse

    companion object {
        operator fun invoke(): ApixuWeatherApi {
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("key", API_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.apixu.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApixuWeatherApi::class.java)
        }
    }
}