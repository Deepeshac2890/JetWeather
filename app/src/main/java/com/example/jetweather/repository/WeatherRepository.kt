package com.example.jetweather.repository

import androidx.room.*
import com.example.jetweather.data.DataOrException
import com.example.jetweather.data.WeatherDao
import com.example.jetweather.model.*
import com.example.jetweather.network.WeatherAPI
import kotlinx.coroutines.flow.Flow
import java.lang.Exception
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api: WeatherAPI,
    private val dao: WeatherDao
) {
    suspend fun getWeather(
        cityQuery: String,
        units: String = "imperial"
    ): DataOrException<Weather, Boolean, Exception> {
        val response = try {
            api.getWeather(cityQuery, units = units)
        } catch (e: Exception) {
            return DataOrException(e = e)
        }
        return DataOrException(data = response)
    }

    fun getFavorites(): Flow<List<Favorite>> = dao.getFavorites()

    suspend fun getFavByCity(cityName: String): Favorite = dao.getFavByCity(cityName = cityName)

    suspend fun insertFavCity(favorite: Favorite) {
        dao.insertFavCity(favorite = favorite)
    }

    suspend fun updateFavCity(favorite: Favorite) {
        dao.updateFavCity(favorite = favorite)
    }

    suspend fun deleteAllCities() {
        dao.deleteAllCities()
    }

    suspend fun deleteFavCity(city: String) {
        dao.deleteFavCity(
            city = city
        )
    }

    fun getUserPreferences(): Flow<List<UserPreferences>> = dao.getUserPreferences()

    suspend fun insertUserPreference(userPreferences: UserPreferences) {
        dao.insertUserPreference(userPreferences = userPreferences)
    }
    suspend fun updateUserPreference(userPreferences: UserPreferences) =
        dao.updateUserPreference(userPreferences = userPreferences)

    suspend fun deleteAllPreferences() = dao.deleteAllPreferences()
}