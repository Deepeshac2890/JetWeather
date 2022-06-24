package com.example.jetweather.data

import androidx.room.*
import com.example.jetweather.model.Favorite
import com.example.jetweather.model.UserPreferences
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    // Here we specify the CRUD operations that we are going to perform

    @Query("select * from fav_tbl")
    fun getFavorites() : Flow<List<Favorite>>

    @Query("select * from fav_tbl where city = :cityName")
    suspend fun getFavByCity(cityName: String) : Favorite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavCity(favorite: Favorite)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavCity(favorite: Favorite)

    @Query("Delete from fav_tbl")
    suspend fun deleteAllCities()

    @Query("Delete from fav_tbl where city=:city")
    suspend fun deleteFavCity(city: String)

    @Query("select * from preference_tbl")
    fun getUserPreferences() : Flow<List<UserPreferences>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUserPreference(userPreferences: UserPreferences)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserPreference(userPreferences: UserPreferences)

    @Query("Delete from preference_tbl")
    suspend fun deleteAllPreferences()
}