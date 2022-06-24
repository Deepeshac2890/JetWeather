package com.example.jetweather.network

import com.example.jetweather.model.Weather
import com.example.jetweather.model.WeatherObject
import com.example.jetweather.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherAPI {
    @GET(value = "data/2.5/forecast/daily")
    suspend fun getWeather(
        @Query(value = "q") query: String,
        @Query(value = "units") units: String = "imperial",
        @Query(value = "appid") appid: String = Constants.API_KEY
    ) : Weather
}